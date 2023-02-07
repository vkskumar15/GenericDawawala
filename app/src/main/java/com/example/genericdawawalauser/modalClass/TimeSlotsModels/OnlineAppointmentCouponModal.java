package com.example.genericdawawalauser.modalClass.TimeSlotsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OnlineAppointmentCouponModal {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

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

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
    public class Detail {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("docVenId")
        @Expose
        private String docVenId;
        @SerializedName("coupon_name")
        @Expose
        private String couponName;
        @SerializedName("percentage")
        @Expose
        private String percentage;
        @SerializedName("min_order_amount")
        @Expose
        private String minOrderAmount;
        @SerializedName("expire")
        @Expose
        private String expire;
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

        public String getDocVenId() {
            return docVenId;
        }

        public void setDocVenId(String docVenId) {
            this.docVenId = docVenId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getMinOrderAmount() {
            return minOrderAmount;
        }

        public void setMinOrderAmount(String minOrderAmount) {
            this.minOrderAmount = minOrderAmount;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
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
