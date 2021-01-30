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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.Status;
import model.Supplier;

/**
 *
 * @author Flynn
 */
public class SupplierDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1 = null;
    Statement stmt1 = null;
    ResultSet rs = null;
    ArrayList<Supplier> suppliers;

    public SupplierDAO() {
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
            String query = "SELECT * FROM suppliers " + "WHERE " + condition;
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ArrayList<Supplier> convertToArrayList(ResultSet rs) {
        try {
            suppliers = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierCode(rs.getString("supplierCode"));
                supplier.setSupplierContact(rs.getString("supplierContact"));
                supplier.setSupplierLocation(rs.getString("supplierLocation"));
                supplier.setSupplierName(rs.getString("supplierName"));
                suppliers.add(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }
}
