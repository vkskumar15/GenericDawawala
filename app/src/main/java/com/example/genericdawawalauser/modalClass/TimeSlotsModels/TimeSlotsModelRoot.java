package com.example.genericdawawalauser.modalClass.TimeSlotsModels;

import java.io.Serializable;

public class TimeSlotsModelRoot implements Serializable {

    public String success;
    public String message;
    public TimeSlotsModelDetails details;

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

    public TimeSlotsModelDetails getDetails() {
        return details;
    }

    public void setDetails(TimeSlotsModelDetails details) {
        this.details = details;
    }
}
