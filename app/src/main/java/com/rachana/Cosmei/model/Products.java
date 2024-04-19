package com.rachana.Cosmei.model;

import java.io.Serializable;

public class Products implements Serializable {

    Integer productid;
    String productName;
    String productQty;
    Float productPrice;
    Integer imageUrl;
    String productDescription;


    public Products(Integer productid, String productName, String productQty, Float productPrice, Integer imageUrl, String productDescription) {

        this.productid = productid;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.productDescription= productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQty() {
        return productQty;
    }

    public String getProductDescription() {
        return productDescription;
    }


    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}