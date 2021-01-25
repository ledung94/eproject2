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
import model.Purchase;
import model.Supplier;

/**
 *
 * @author Flynn
 */
public class PurchaseDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1 = null;
    Statement stmt1 = null;
    ResultSet rs = null;
    ArrayList<Purchase> purchase = null;

    public PurchaseDAO() {
        try {
            con = new connectiondb().getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getInfo(String table, String field, String condition) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM "+ table + " "  + condition;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(field));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
