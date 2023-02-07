package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyCouponAppointment {

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

        @SerializedName("actualPrice")
        @Expose
        private String actualPrice;
        @SerializedName("discountPrice")
        @Expose
        private String discountPrice;
        @SerializedName("discountInPer")
        @Expose
        private String discountInPer;
        @SerializedName("payAmount")
        @Expose
        private String payAmount;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("couponCode")
        @Expose
        private String couponCode;
        @SerializedName("couponId")
        @Expose
        private String couponId;
        @SerializedName("verifiedId")
        @Expose
        private String verifiedId;

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getDiscountInPer() {
            return discountInPer;
        }

        public void setDiscountInPer(String discountInPer) {
            this.discountInPer = discountInPer;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getVerifiedId() {
            return verifiedId;
        }

        public void setVerifiedId(String verifiedId) {
            this.verifiedId = verifiedId;
        }

    }
}
