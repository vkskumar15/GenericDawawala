package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddCartLabModal {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details;

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
        @SerializedName("categoryId")
        @Expose
        private String categoryId;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("aboutTest")
        @Expose
        private String aboutTest;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("homeCollectionCheck")
        @Expose
        private String homeCollectionCheck;
        @SerializedName("prescriptionCheck")
        @Expose
        private String prescriptionCheck;
        @SerializedName("addToCart_status")
        @Expose
        private String addToCartStatus;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("actualPrice")
        @Expose
        private String actualPrice;
        @SerializedName("discount")
        @Expose
        private Integer discount;
        @SerializedName("discountPrice")
        @Expose
        private Integer discountPrice;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAboutTest() {
            return aboutTest;
        }

        public void setAboutTest(String aboutTest) {
            this.aboutTest = aboutTest;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getHomeCollectionCheck() {
            return homeCollectionCheck;
        }

        public void setHomeCollectionCheck(String homeCollectionCheck) {
            this.homeCollectionCheck = homeCollectionCheck;
        }

        public String getPrescriptionCheck() {
            return prescriptionCheck;
        }

        public void setPrescriptionCheck(String prescriptionCheck) {
            this.prescriptionCheck = prescriptionCheck;
        }

        public String getAddToCartStatus() {
            return addToCartStatus;
        }

        public void setAddToCartStatus(String addToCartStatus) {
            this.addToCartStatus = addToCartStatus;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

        public Integer getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(Integer discountPrice) {
            this.discountPrice = discountPrice;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
        }

    }
}
