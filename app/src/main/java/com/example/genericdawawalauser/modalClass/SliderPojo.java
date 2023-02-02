package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SliderPojo {
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String success2) {
        this.success = success2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public List<Detail> getDetails() {
        return this.details;
    }

    public void setDetails(List<Detail> details2) {
        this.details = details2;
    }

    public class Detail {
        @SerializedName("id")
        @Expose

        /* renamed from: id */
        private String f49id;
        @SerializedName("sliderImage")
        @Expose
        private String sliderImage;
        @SerializedName("title")
        @Expose
        private String title;

        public Detail() {
        }

        public String getId() {
            return this.f49id;
        }

        public void setId(String id) {
            this.f49id = id;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title2) {
            this.title = title2;
        }

        public String getSliderImage() {
            return this.sliderImage;
        }

        public void setSliderImage(String sliderImage2) {
            this.sliderImage = sliderImage2;
        }
    }
}
