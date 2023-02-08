package com.example.genericdawawalauser.retrofit;

import com.example.genericdawawalauser.modalClass.ApplyCouponAppointment;
import com.example.genericdawawalauser.modalClass.ChangePasswordModal;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.modalClass.GenerateOrderIdModel;
import com.example.genericdawawalauser.modalClass.HealthProblemModal;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;
import com.example.genericdawawalauser.modalClass.ReScheduledAppointment;
import com.example.genericdawawalauser.modalClass.RegisterModelRoot;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.CancelOnlineAppointment;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentCouponModal;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentModal;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.TimeSlotsModelRoot;
import com.example.genericdawawalauser.modalClass.UniqueAPiModel;
import com.example.genericdawawalauser.modalClass.UpdateUserPhoneModel;
import com.example.genericdawawalauser.modalClass.WalletAmountModal;
import com.example.genericdawawalauser.modalClass.WalletHistoryModal;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("uniqueAPI")
    Call<UniqueAPiModel> checkEmailPhone(@Field("email") String str, @Field("phone") String str2);

    @FormUrlEncoded
    @POST("userLogin")
    Call<RegisterModelRoot> loginUser(@Field("emailPhone") String emailPhone,
                                      @Field("password") String password,
                                      @Field("reg_id") String reg_id,
                                      @Field("device_id") String device_id,
                                      @Field("login_type") String login_type,
                                      @Field("latitude") String latitude,
                                      @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("changePhoneEmail")
    Call<UpdateUserPhoneModel> phoneEmailVerification(@Field("emailPhone") String str);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterModelRoot> registerUser(@Field("username") String str, @Field("email") String str2, @Field("phone") String str3, @Field("password") String str4, @Field("reg_id") String str5, @Field("device_id") String str6, @Field("login_type") String str7, @Field("latitude") String str8, @Field("longitude") String str9, @Field("chatId") String str10);

    @FormUrlEncoded
    @POST("updatePhoneEmail")
    Call<ChangePasswordModal> updatePhoneEmail(@Field("password") String str);

    @POST("updateProfile")
    @Multipart
    Call<RegisterModelRoot> updateUserData(@Part("userId") RequestBody requestBody, @Part("username") RequestBody requestBody2, @Part("gender") RequestBody requestBody3, @Part("email") RequestBody requestBody4, @Part("dob") RequestBody requestBody5, @Part("phone") RequestBody requestBody6, @Part("address") RequestBody requestBody7, @Part MultipartBody.Part part);


    @GET("getDoctorsSpecialistToUser")
    Call<DoctorModelRoot> getDoctorSpecialities();

    @FormUrlEncoded
    @POST("getDoctorVendorToUser")
    Call<DoctorModelRoot> getDoctorsAsPerSpeciality(
            @Field("dr_speciality") String dr_speciality,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );

    @FormUrlEncoded
    @POST("vendorDoctorAvailabilitySlots")
    Call<TimeSlotsModelRoot> getDoctorSlots(
            @Field("availableDate") String availableDate,
            @Field("doctor_Id") String doctor_Id
    );

    @FormUrlEncoded
    @POST("getUserWallet")
    Call<WalletAmountModal> getUserWallet(
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("orderIdGenerate")
    Call<GenerateOrderIdModel> generateOrderId(
            @Field("amount") String amount
    );

    @FormUrlEncoded
    @POST("addUserWallet")
    Call<WalletAmountModal> addUserWallet(
            @Field("userId") String userId,
            @Field("wallet_amount") String wallet_amount,
            @Field("razorpay_order_id") String razorpay_order_id,
            @Field("razorpay_payment_id") String razorpay_payment_id,
            @Field("razorpay_signature") String razorpay_signature

    );


    @FormUrlEncoded
    @POST("userWalletHistory")
    Call<WalletHistoryModal> userWalletHistory(
            @Field("userId") String userId
    );


    @FormUrlEncoded
    @POST("DoctorAppointment")
    Call<OnlineAppointmentModal> DoctorAppointment(
            @Field("userId") String userId,
            @Field("docId") String docId,
            @Field("releation") String releation,
            @Field("patient_gender") String patient_gender,
            @Field("patient_name") String patient_name,
            @Field("patient_age") String patient_age,
            @Field("patient_number") String patient_number,
            @Field("healthProblem") String healthProblem,
            @Field("appointmentDate") String appointmentDate,
            @Field("amount") String amount,
            @Field("AppointmentType") String AppointmentType,
            @Field("specialty") String specialty,
            @Field("coupanVerifiedId") String coupanVerifiedId

    );


    @FormUrlEncoded
    @POST("pendingDocAppointment")
    Call<PendingOnlineAppointmentModal> pendingDocAppointment(
            @Field("userId") String userId,
            @Field("type") String type
    );


    @FormUrlEncoded
    @POST("appointmentCancelByUser")
    Call<CancelOnlineAppointment> appointmentCancelByUser(
            @Field("userId") String userId,
            @Field("appointmentId") String appointmentId
    );


    @FormUrlEncoded
    @POST("getCouponForUser")
    Call<OnlineAppointmentCouponModal> getCouponForUser(
            @Field("docId") String docId
    );

    @FormUrlEncoded
    @POST("reedemCoupon")
    Call<ApplyCouponAppointment> reedemCoupon(
            @Field("coupon_name") String coupon_name,
            @Field("amount") String amount,
            @Field("userId") String userId
    );


    @FormUrlEncoded
    @POST("reScheduledAppointment")
    Call<ReScheduledAppointment> reScheduledAppointment(
            @Field("userId") String userId,
            @Field("appointmentDate") String appointmentDate,
            @Field("appointmentId") String appointmentId);

    @GET("getAllergies")
    Call<HealthProblemModal> getAllergies();

}
