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
public class Purchase {

    private String purchaseCode;
    private String supplierCode;
    private String productCode;
    private int productQuantity;
    private float totalCost;
    private String purchaseDate;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
