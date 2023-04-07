package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RadioSubCatModal {

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
        @SerializedName("radId")
        @Expose
        private String radId;
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
        @SerializedName("created")
        @Expose
        private String created;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRadId() {
            return radId;
        }

        public void setRadId(String radId) {
            this.radId = radId;
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

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

    }
}
