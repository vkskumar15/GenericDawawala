package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LabDetailsModal {

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

        @SerializedName("actualPrice")
        @Expose
        private String actualPrice;
        @SerializedName("LabId")
        @Expose
        private String labId;
        @SerializedName("testIds")
        @Expose
        private String testIds;
        @SerializedName("totalTests")
        @Expose
        private String totalTests;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("about")
        @Expose
        private String about;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created")
        @Expose
        private String created;

        @SerializedName("updated")
        @Expose
        private String updated;
        
        @SerializedName("PrescriptionCheck")
        @Expose
        private String PrescriptionCheck;


        @SerializedName("discountPrice")
        @Expose
        private Integer discountPrice;
        @SerializedName("discount")
        @Expose
        private Integer discount;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;
        @SerializedName("Test")
        @Expose
        private List<Test> test;

        public String getPrescriptionCheck() {
            return PrescriptionCheck;
        }

        public void setPrescriptionCheck(String PrescriptionCheck) {
            this.PrescriptionCheck = PrescriptionCheck;
        }

        public String getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(String actualPrice) {
            this.actualPrice = actualPrice;
        }

        public String getLabId() {
            return labId;
        }

        public void setLabId(String labId) {
            this.labId = labId;
        }

        public String getTestIds() {
            return testIds;
        }

        public void setTestIds(String testIds) {
            this.testIds = testIds;
        }

        public String getTotalTests() {
            return totalTests;
        }

        public void setTotalTests(String totalTests) {
            this.totalTests = totalTests;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public Integer getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(Integer discountPrice) {
            this.discountPrice = discountPrice;
        }

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
        }

        public List<Test> getTest() {
            return test;
        }

        public void setTest(List<Test> test) {
            this.test = test;
        }

        public class Test {

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
            @SerializedName("PrescriptionCheck")
            @Expose
            private String PrescriptionCheck;
            @SerializedName("addToCart_status")
            @Expose
            private String addToCartStatus;
            @SerializedName("created")
            @Expose
            private String created;

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
                return PrescriptionCheck;
            }

            public void setPrescriptionCheck(String PrescriptionCheck) {
                this.PrescriptionCheck = PrescriptionCheck;
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

        }
    }
}
