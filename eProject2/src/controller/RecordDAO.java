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
import model.RecordType;
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
            record.setRecordCode("RC" + Integer.toString(new RecordDAO().getInfo("records", "recordID", "").size() + 1));
            String q = "INSERT INTO records VALUES(null,?,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(q);
            pstmt.setString(1, record.getRecordCode());
            pstmt.setString(2, record.getRecordType().toString());
            pstmt.setInt(3, record.getSupplierID());
            pstmt.setInt(4, record.getCustomerID());
            pstmt.setInt(5, record.getHandleBy());
            pstmt.setString(6, record.getDate());
            pstmt.setFloat(7, record.getTotalPrice());
            pstmt.setInt(8, record.getVat());

            pstmt.executeUpdate();

            String recordID = new RecordDAO().getInfo("records", "recordID", "WHERE recordCode = '" + record.getRecordCode() + "'").get(0);
            for (RecordDetail recordDetail : recordDetails) {
                recordDetail.setRecordID(Integer.parseInt(recordID));
                addRecordDetail(recordDetail);
            }
            JOptionPane.showMessageDialog(null, "Successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(RecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Record getSearchRecordQueryResult(String searchTxt) {
        try {
            String query = "SELECT * FROM records WHERE recordID = '" + searchTxt + "' OR recordCode = '" + searchTxt + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                record = new Record();
                record.setCustomerID(rs.getInt("customerID"));
                record.setDate(rs.getString("date"));
                record.setSupplierID(rs.getInt("supplierID"));
                record.setHandleBy(rs.getInt("handleBy"));
                record.setRecordCode(rs.getString("recordCode"));
                record.setRecordID(rs.getInt("recordID"));
                record.setRecordType(RecordType.valueOf(rs.getString("recordType")));
                record.setVat(rs.getInt("vat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public ArrayList<RecordDetail> getSearchRecordDetailQueryResult(Record record) {
        try {
            String query = "SELECT * FROM recordDetail WHERE recordID = '" + record.getRecordID() + "'";
            rs = stmt.executeQuery(query);
            recordDetails = new ArrayList<>();
            while (rs.next()) {
                RecordDetail rcdt = new RecordDetail();
                rcdt.setProductID(rs.getInt("productID"));
                rcdt.setQuantity(Integer.parseInt(rs.getString("quantity")));
                rcdt.setRecordID(Integer.parseInt(rs.getString("recordID")));
                recordDetails.add(rcdt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordDetails;
    }

    public void deleteRecord(Record record) {
        try {
            String query = "UPDATE records SET recordType = 'DELETED' WHERE recordID=?";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setInt(1, record.getRecordID());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Delete Successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(RecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
