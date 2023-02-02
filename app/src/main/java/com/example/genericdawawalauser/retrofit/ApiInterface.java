package com.example.genericdawawalauser.retrofit;

import com.example.genericdawawalauser.modalClass.ChangePasswordModal;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.modalClass.RegisterModelRoot;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.TimeSlotsModelRoot;
import com.example.genericdawawalauser.modalClass.UniqueAPiModel;
import com.example.genericdawawalauser.modalClass.UpdateUserPhoneModel;

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

}