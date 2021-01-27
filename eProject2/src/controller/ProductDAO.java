/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import eproject2.connection.connectiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.internal.org.objectweb.asm.Opcodes;
import model.Product;
import model.ProductStatus;

/**
 *
 * @author Flynn
 */
public class ProductDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1 = null;
    Statement stmt1 = null;
    ResultSet rs = null;
    ArrayList<Product> products = null;

    public ProductDAO() {
        try {
            con = new connectiondb().getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getQueryResult(String condition) {
        try {
            String query = "SELECT * FROM products " + "WHERE " + condition;
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void addProductDAO(Product product) {
        try {
            String query = "SELECT * FROM products WHERE productName='" + product.getProductName() + "' AND productCode='" + product.getProductCode() + "' AND category='" + product.getProductCategory() + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Same Product has already been added!");
            } else {
                addFunction(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addFunction(Product product) {
        try {

            String q = "INSERT INTO products VALUES(null,?,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(q);
//            pstmt.setString(1, product.getProductID());
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductCode());
            pstmt.setDouble(3, product.getCostPrice());
            pstmt.setDouble(4, product.getSellingPrice());
            pstmt.setString(5, product.getProductCategory());
            pstmt.setString(6, product.getProductImage());
            pstmt.setString(7, product.getProductStatus().name());
            pstmt.setString(8, product.getDate());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted Successfully! Now you can purchase the product..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductDAO(Product product) {
        try {
            String query = "UPDATE products SET status = 'REMOVE' WHERE productCode=?";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setString(1, product.getProductCode());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void editProductDAO(Product product) {
        try {
            String query = "UPDATE products SET productName=?,costprice=?,sellingprice=?,category=?,productImage=? WHERE productCode=?";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setString(1, product.getProductName());
//            pstmt.setString(2, product.getProductCode());
            pstmt.setDouble(2, product.getCostPrice());
            pstmt.setDouble(3, product.getSellingPrice());
            pstmt.setString(4, product.getProductCategory());
            pstmt.setString(5, product.getProductImage());
            pstmt.setString(6, product.getProductCode());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet getSearchProductsQueryResult(String searchTxt) {
        try {
            String query = "SELECT productImage,productCode,productName,costPrice,sellingPrice,category,date,status FROM products WHERE productName LIKE '%" + searchTxt + "%' OR category LIKE '%" + searchTxt + "%' OR status LIKE '%" + searchTxt + "%' OR productCode LIKE '%" + searchTxt + "%'"  ;
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ArrayList<Product> convertToArrayList(ResultSet rs) {
        try {
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(Integer.parseInt(rs.getString("productID")));
                product.setProductName(rs.getString("productName"));
                product.setProductCode(rs.getString("productCode"));
                product.setCostPrice(rs.getFloat("costPrice"));
                product.setSellingPrice(rs.getFloat("sellingPrice"));
                product.setProductCategory(rs.getString("category"));
                product.setDate(rs.getString("date"));
                product.setProductStatus(ProductStatus.valueOf(rs.getString("status")));
                product.setProductImage(rs.getString("productImage"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

}
