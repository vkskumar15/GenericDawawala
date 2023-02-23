package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabBookModal {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
    public class Details {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("labId")
        @Expose
        private String labId;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("patient_id")
        @Expose
        private String patientId;
        @SerializedName("appointmentId")
        @Expose
        private String appointmentId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time_slot")
        @Expose
        private String timeSlot;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("result_status")
        @Expose
        private String resultStatus;
        @SerializedName("reason")
        @Expose
        private String reason;
        @SerializedName("homeCollection")
        @Expose
        private String homeCollection;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("updated")
        @Expose
        private String updated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabId() {
            return labId;
        }

        public void setLabId(String labId) {
            this.labId = labId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(String timeSlot) {
            this.timeSlot = timeSlot;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(String resultStatus) {
            this.resultStatus = resultStatus;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getHomeCollection() {
            return homeCollection;
        }

        public void setHomeCollection(String homeCollection) {
            this.homeCollection = homeCollection;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

    }
}
