package com.example.genericdawawalauser.modalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class AddToCartPackageModal {

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
            @SerializedName("packageId")
            @Expose
            private String packageId;

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

            public String getPackageId() {
                return packageId;
            }

            public void setPackageId(String packageId) {
                this.packageId = packageId;
            }
    }

}
