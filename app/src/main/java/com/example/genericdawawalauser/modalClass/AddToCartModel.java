package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCartModel {

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
        @SerializedName("medicineId")
        @Expose
        private String medicineId;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("medicinePrice")
        @Expose
        private String medicinePrice;
        @SerializedName("finalPrice")
        @Expose
        private String finalPrice;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

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

        public String getMedicineId() {
            return medicineId;
        }

        public void setMedicineId(String medicineId) {
            this.medicineId = medicineId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getMedicinePrice() {
            return medicinePrice;
        }

        public void setMedicinePrice(String medicinePrice) {
            this.medicinePrice = medicinePrice;
        }

        public String getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(String finalPrice) {
            this.finalPrice = finalPrice;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }
}
