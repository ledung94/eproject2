/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Flynn
 */
public class Record {
    private String recordID;
    private String recordCode;
    private RecordType recordType;
    private int supplierID;
    private int customerID;
    private int handleBy;
    private String date;
    private float totalPrice;

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setHandleBy(int handleBy) {
        this.handleBy = handleBy;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getHandleBy() {
        return handleBy;
    }

    public String getDate() {
        return date;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    
    
    
    
}
