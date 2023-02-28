package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserLabModal {

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
        @SerializedName("fullAddress")
        @Expose
        private String fullAddress;
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

        public String getFullAddress() {
            return fullAddress;
        }

        public void setFullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
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
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("visit")
            @Expose
            private String visit;
            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("age")
            @Expose
            private String age;
            @SerializedName("fasting")
            @Expose
            private String fasting;
            @SerializedName("about")
            @Expose
            private String about;
            @SerializedName("prescription")
            @Expose
            private String prescription;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVisit() {
                return visit;
            }

            public void setVisit(String visit) {
                this.visit = visit;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getFasting() {
                return fasting;
            }

            public void setFasting(String fasting) {
                this.fasting = fasting;
            }

            public String getAbout() {
                return about;
            }

            public void setAbout(String about) {
                this.about = about;
            }

            public String getPrescription() {
                return prescription;
            }

            public void setPrescription(String prescription) {
                this.prescription = prescription;
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

}
