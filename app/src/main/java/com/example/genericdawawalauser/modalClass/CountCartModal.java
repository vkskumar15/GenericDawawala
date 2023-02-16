package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountCartModal {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product_counts")
    @Expose
    private Integer productCounts;
    @SerializedName("TotalCartPrice")
    @Expose
    private String totalCartPrice;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getProductCounts() {
        return productCounts;
    }

    public void setProductCounts(Integer productCounts) {
        this.productCounts = productCounts;
    }

    public String getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(String totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

}
