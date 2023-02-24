package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RadiologyPackageTestModal {

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
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("updated")
        @Expose
        private String updated;
        @SerializedName("totalTests")
        @Expose
        private Integer totalTests;
        @SerializedName("tests")
        @Expose
        private List<Test> tests;

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

        public Integer getTotalTests() {
            return totalTests;
        }

        public void setTotalTests(Integer totalTests) {
            this.totalTests = totalTests;
        }

        public List<Test> getTests() {
            return tests;
        }

        public void setTests(List<Test> tests) {
            this.tests = tests;
        }
        public class Test {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("packageId")
            @Expose
            private String packageId;
            @SerializedName("Testname")
            @Expose
            private String testname;
            @SerializedName("created")
            @Expose
            private String created;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPackageId() {
                return packageId;
            }

            public void setPackageId(String packageId) {
                this.packageId = packageId;
            }

            public String getTestname() {
                return testname;
            }

            public void setTestname(String testname) {
                this.testname = testname;
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
