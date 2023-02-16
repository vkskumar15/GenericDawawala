package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLabCategoryModal {

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

        @SerializedName("LabId")
        @Expose
        private String labId;
        @SerializedName("labTestCatId")
        @Expose
        private String labTestCatId;
        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getLabId() {
            return labId;
        }

        public void setLabId(String labId) {
            this.labId = labId;
        }

        public String getLabTestCatId() {
            return labTestCatId;
        }

        public void setLabTestCatId(String labTestCatId) {
            this.labTestCatId = labTestCatId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

    }
}
