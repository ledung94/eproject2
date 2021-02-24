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
import model.Customer;

/**
 *
 * @author THINH PC
 */
public class CustomerDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1 = null;
    Statement stmt1 = null;
    ResultSet rs = null;
    ArrayList<Customer> customers;

    public CustomerDAO() {
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
            String query = "SELECT * FROM customers " + "WHERE " + condition;
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ArrayList<Customer> convertToArrayList(ResultSet rs) {
        try {
            customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setCustomerCode(rs.getString("customerCode"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setCustomerAddress(rs.getString("customerAddress"));
                customer.setCustomerPhone(rs.getString("customerPhone"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customers;
    }
    
    public ResultSet getSearchCustomerQueryResult(String searchTxt) {
        try {
            String query = "SELECT * FROM customers WHERE customerCode LIKE '%" + searchTxt + "%' OR customerName LIKE '%" + searchTxt + "%'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}