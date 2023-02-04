package com.example.genericdawawalauser.modalClass.TimeSlotsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnlineAppointmentModal {

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
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("docId")
        @Expose
        private String docId;
        @SerializedName("appointmentId")
        @Expose
        private String appointmentId;
        @SerializedName("releation")
        @Expose
        private String releation;
        @SerializedName("patient_gender")
        @Expose
        private String patientGender;
        @SerializedName("patient_name")
        @Expose
        private String patientName;
        @SerializedName("patient_age")
        @Expose
        private String patientAge;
        @SerializedName("patient_number")
        @Expose
        private String patientNumber;
        @SerializedName("healthProblem")
        @Expose
        private String healthProblem;
        @SerializedName("appointmentDate")
        @Expose
        private String appointmentDate;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("status")
        @Expose
        private String status;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
        }

        public String getReleation() {
            return releation;
        }

        public void setReleation(String releation) {
            this.releation = releation;
        }

        public String getPatientGender() {
            return patientGender;
        }

        public void setPatientGender(String patientGender) {
            this.patientGender = patientGender;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientAge() {
            return patientAge;
        }

        public void setPatientAge(String patientAge) {
            this.patientAge = patientAge;
        }

        public String getPatientNumber() {
            return patientNumber;
        }

        public void setPatientNumber(String patientNumber) {
            this.patientNumber = patientNumber;
        }

        public String getHealthProblem() {
            return healthProblem;
        }

        public void setHealthProblem(String healthProblem) {
            this.healthProblem = healthProblem;
        }

        public String getAppointmentDate() {
            return appointmentDate;
        }

        public void setAppointmentDate(String appointmentDate) {
            this.appointmentDate = appointmentDate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
