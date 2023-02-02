package com.example.genericdawawalauser.modalClass;

import java.io.Serializable;
import java.util.List;

public class DoctorModelRoot implements Serializable {

    public String success;
    public String message;
    public List<DoctorModelDetails> details;

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

    public List<DoctorModelDetails> getDetails() {
        return details;
    }

    public void setDetails(List<DoctorModelDetails> details) {
        this.details = details;
    }
}
