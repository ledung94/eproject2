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
import javax.swing.JOptionPane;
import model.Product;
import model.RecordType;

/**
 *
 * @author Flynn
 */
public class CurrentStockDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1 = null;
    Statement stmt1 = null;
    ResultSet rs = null;

    public CurrentStockDAO() {
        try {
            con = new connectiondb().getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCurrentStock(int productID) {
        int quantity = 0;
        try {
            String query = "SELECT * FROM currentStocks WHERE productID = '" + productID + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }

    public void updateCurrentStock(int productID, int number, RecordType recordType) {
        try {
            String calculation = "+";
            if (recordType.equals(RecordType.EXPORT)) {
                calculation = "-";
            }
            String query = "UPDATE currentStocks SET quantity = quantity " + calculation + number + " WHERE productID = '" + productID + "'";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialCurrentStock(Product product) {
        try {
            String q = "INSERT INTO currentStocks VALUES(?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(q);
            pstmt.setInt(1, product.getProductID());
            pstmt.setInt(2, 0);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



