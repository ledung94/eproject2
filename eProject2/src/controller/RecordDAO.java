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
import model.Record;
import model.RecordDetail;
import model.Supplier;

/**
 *
 * @author Flynn
 */
public class RecordDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs1 = null;
    Statement stmt1 = null;
    ResultSet rs = null;
    ArrayList<RecordDetail> recordDetails;
    Record record;

    public RecordDAO() {
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
            String query = "SELECT * FROM " + table + " " + condition;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(field));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addRecordDetail(RecordDetail recordDetail) {
        try {
            String q = "INSERT INTO recordDetail VALUES(?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(q);
            pstmt.setInt(1, recordDetail.getProductID());
            pstmt.setInt(2, recordDetail.getQuantity());
            pstmt.setInt(3, recordDetail.getRecordID());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRecord(Record record, ArrayList<RecordDetail> recordDetails) {
        try {
            record.setRecordCode("RC" + Integer.toString(Integer.parseInt(new RecordDAO().getInfo("records", "recordID", "WHERE recordID = (SELECT MAX(recordID) FROM records)").get(0)) + 1));
            String q = "INSERT INTO records VALUES(null,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(q);
//            pstmt.setString(1, record.getRecordID());
            pstmt.setString(1, record.getRecordCode());
            pstmt.setString(2, record.getRecordType().toString());
            pstmt.setInt(3, record.getSupplierID());
            pstmt.setInt(4, record.getCustomerID());
            pstmt.setInt(5, record.getHandleBy());
            pstmt.setString(6, record.getDate());
            pstmt.setFloat(7, record.getTotalPrice());

            pstmt.executeUpdate();

            String recordID = new RecordDAO().getInfo("records", "recordID", "WHERE recordCode = '" + record.getRecordCode() + "'").get(0);
            for (RecordDetail recordDetail : recordDetails) {
                recordDetail.setRecordID(Integer.parseInt(recordID));
                System.out.println(recordDetail.getProductID());
                addRecordDetail(recordDetail);
            }
            JOptionPane.showMessageDialog(null, "Successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(RecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getSearchRecordQueryResult(String searchTxt) {
        try {
            String query = "SELECT * FROM records WHERE recordID = '" + searchTxt + "' OR recordCode = '" + searchTxt + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {                
                record = new Record();
                record.setCustomerID(rs.getInt("customerID"));
                record.setDate(rs.getString("date"));
                record.setHandleBy(rs.getInt("handleBy"));
                record.setRecordCode(rs.getString("recordCode"));
                record.setRecordID(rs.getInt("recordID"));
                record.setRecordType(rs.getString("recordType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getSearchRecordDetailQueryResult(Record record) {
        try {
            String query = "SELECT productID, quantity FROM recordDetail WHERE recordID = '" + record.getRecordID() + "'";
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
