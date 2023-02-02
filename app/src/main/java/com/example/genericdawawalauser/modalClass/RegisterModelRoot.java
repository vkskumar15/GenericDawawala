package com.example.genericdawawalauser.modalClass;

import java.io.Serializable;

public class RegisterModelRoot implements Serializable {
    public RegisterModel details;
    public String message;
    public String otp;
    public String success;

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp2) {
        this.otp = otp2;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success2) {
        this.success = success2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public RegisterModel getDetails() {
        return this.details;
    }

    public void setDetails(RegisterModel details2) {
        this.details = details2;
    }
}
