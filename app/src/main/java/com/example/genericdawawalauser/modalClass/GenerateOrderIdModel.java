package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateOrderIdModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("key")
    @Expose
    private String key;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
