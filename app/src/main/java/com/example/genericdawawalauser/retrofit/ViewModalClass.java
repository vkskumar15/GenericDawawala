package com.example.genericdawawalauser.retrofit;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.genericdawawalauser.modalClass.AddCartLabModal;
import com.example.genericdawawalauser.modalClass.AddFamilyMember;
import com.example.genericdawawalauser.modalClass.AddPatientDetails;
import com.example.genericdawawalauser.modalClass.AddToCartModal;
import com.example.genericdawawalauser.modalClass.AddToCartPackageModal;
import com.example.genericdawawalauser.modalClass.ApplyCouponAppointment;
import com.example.genericdawawalauser.modalClass.ChangePasswordModal;
import com.example.genericdawawalauser.modalClass.CountCartModal;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.modalClass.GenerateOrderIdModel;
import com.example.genericdawawalauser.modalClass.GetFamilyMemberModal;
import com.example.genericdawawalauser.modalClass.GetLabCategoryModal;
import com.example.genericdawawalauser.modalClass.GetPatientAddress;
import com.example.genericdawawalauser.modalClass.GetUserLabModal;
import com.example.genericdawawalauser.modalClass.HealthProblemModal;
import com.example.genericdawawalauser.modalClass.LabBookModal;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;
import com.example.genericdawawalauser.modalClass.LabPackageDetailsModal;
import com.example.genericdawawalauser.modalClass.LabTestCategories;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;
import com.example.genericdawawalauser.modalClass.RadioSubCatModal;
import com.example.genericdawawalauser.modalClass.RadiologyCategoryModal;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;
import com.example.genericdawawalauser.modalClass.ReScheduledAppointment;
import com.example.genericdawawalauser.modalClass.RegisterModelRoot;
import com.example.genericdawawalauser.modalClass.RemoveCartModal;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.CancelOnlineAppointment;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentCouponModal;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentModal;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.TimeSlotsModelRoot;
import com.example.genericdawawalauser.modalClass.UniqueAPiModel;
import com.example.genericdawawalauser.modalClass.UpdateUserPhoneModel;
import com.example.genericdawawalauser.modalClass.WalletAmountModal;
import com.example.genericdawawalauser.modalClass.WalletHistoryModal;
import com.example.genericdawawalauser.utils.CommonUtils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModalClass extends ViewModel {
    ApiInterface apiInterface = BaseUrlRetrofit.getRetrofit().create(ApiInterface.class);

    private MutableLiveData<RegisterModelRoot> registerModelRootMutableLiveData;

    private MutableLiveData<RegisterModelRoot> registerModelRootMutableLiveData2;
    private MutableLiveData<UniqueAPiModel> uniqueAPiModelMutableLiveData;
    private MutableLiveData<RegisterModelRoot> updateUserDataModelRootMutableLiveData;
    private MutableLiveData<ChangePasswordModal> updateUserEmailPhoneModelMutableLiveData;
    private MutableLiveData<UpdateUserPhoneModel> updateUserPhoneModelMutableLiveData;

    public LiveData<UniqueAPiModel> uniqueAPiModelLiveData(final Activity activity, String email, String phone) {

        uniqueAPiModelMutableLiveData = new MutableLiveData<>();

        apiInterface.checkEmailPhone(email, phone).enqueue(new Callback<UniqueAPiModel>() {
            public void onResponse(Call<UniqueAPiModel> call, Response<UniqueAPiModel> response) {
                if (response.body() != null) {

                    uniqueAPiModelMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<UniqueAPiModel> call, Throwable t) {

                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return uniqueAPiModelMutableLiveData;
    }


    public LiveData<RegisterModelRoot> registerModelRootLiveData(final Activity activity, String user,
                                                                 String email, String phone, String password,
                                                                 String reg_id, String device_id, String login_type,
                                                                 String latitude, String longitude, String chatId) {

        registerModelRootMutableLiveData = new MutableLiveData<>();

        CommonUtils.showProgressDialog(activity);

        apiInterface.registerUser(user, email, phone, password, reg_id, device_id, login_type, latitude, longitude, chatId).enqueue(new Callback<RegisterModelRoot>() {
            public void onResponse(Call<RegisterModelRoot> call, Response<RegisterModelRoot> response) {
                CommonUtils.dismissDialog();

                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.registerModelRootMutableLiveData.postValue(response.body());
                }


            }

            public void onFailure(Call<RegisterModelRoot> call, Throwable t) {

                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.registerModelRootMutableLiveData;
    }


    public LiveData<RegisterModelRoot> loginUserModelRootLiveData(Activity activity, String emailPhone, String password, String reg_id, String device_type, String device_id, String latitude, String longitude) {
        this.registerModelRootMutableLiveData2 = new MutableLiveData<>();
        Activity activity2 = activity;
        CommonUtils.showProgressDialog(activity);
        this.apiInterface.loginUser(emailPhone, password, reg_id, device_type, device_id, latitude, longitude).enqueue(new Callback<RegisterModelRoot>() {
            public void onResponse(Call<RegisterModelRoot> call, Response<RegisterModelRoot> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    ViewModalClass.this.registerModelRootMutableLiveData2.postValue(response.body());
                }
            }

            public void onFailure(Call<RegisterModelRoot> call, Throwable t) {
                CommonUtils.dismissDialog();
                ViewModalClass.this.registerModelRootMutableLiveData2.postValue(null);
            }
        });
        return this.registerModelRootMutableLiveData2;
    }

    public LiveData<UpdateUserPhoneModel> updateUserPhoneModelLiveData(final Activity activity, String emailPhone) {
        this.updateUserPhoneModelMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        this.apiInterface.phoneEmailVerification(emailPhone).enqueue(new Callback<UpdateUserPhoneModel>() {
            public void onResponse(Call<UpdateUserPhoneModel> call, Response<UpdateUserPhoneModel> response) {
                CommonUtils.dismissDialog();
                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.updateUserPhoneModelMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<UpdateUserPhoneModel> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.updateUserPhoneModelMutableLiveData;
    }

    public LiveData<ChangePasswordModal> changePasswordModalLiveData(final Activity activity, String password) {
        this.updateUserEmailPhoneModelMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        this.apiInterface.updatePhoneEmail(password).enqueue(new Callback<ChangePasswordModal>() {
            public void onResponse(Call<ChangePasswordModal> call, Response<ChangePasswordModal> response) {
                CommonUtils.dismissDialog();
                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.updateUserEmailPhoneModelMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<ChangePasswordModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.updateUserEmailPhoneModelMutableLiveData;
    }

    public LiveData<RegisterModelRoot> updateUserDataModelRootLiveData(final Activity activity, RequestBody userId, RequestBody username,
                                                                       RequestBody gender, RequestBody email,
                                                                       RequestBody dob, RequestBody phone,
                                                                       RequestBody address, MultipartBody.Part image) {
        Activity activity2 = activity;
        this.updateUserDataModelRootMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        this.apiInterface.updateUserData(userId, username, gender, email, dob, phone, address, image).enqueue(new Callback<RegisterModelRoot>() {
            public void onResponse(Call<RegisterModelRoot> call, Response<RegisterModelRoot> response) {
                CommonUtils.dismissDialog();
                if (response.body() == null) {
                    CommonUtils.dismissDialog();
                    Toast.makeText(activity, "Technical issue occurred", Toast.LENGTH_SHORT).show();
                } else if (response.body().getSuccess().equalsIgnoreCase("1")) {
                    ViewModalClass.this.updateUserDataModelRootMutableLiveData.postValue(response.body());
                } else {
                    Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            public void onFailure(Call<RegisterModelRoot> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.updateUserDataModelRootMutableLiveData;
    }


    private MutableLiveData<DoctorModelRoot> GetDoctorSpecialitiesMutableLiveData;

    public LiveData<DoctorModelRoot> GetDoctorSpecialitiesLiveData(Activity activity) {
        GetDoctorSpecialitiesMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.getDoctorSpecialities().enqueue(new Callback<DoctorModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<DoctorModelRoot> call, Response<DoctorModelRoot> response) {
                CommonUtils.dismissDialog();

                if (response.body() != null) {

                    if (response.body().getSuccess().equals("0")) {

                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        GetDoctorSpecialitiesMutableLiveData.postValue(response.body());

                    }

                } else {

                    Toast.makeText(activity, "Services not found", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<DoctorModelRoot> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return GetDoctorSpecialitiesMutableLiveData;

    }


    private MutableLiveData<LabTestCategories> labTestCategoriesMutableLiveData;

    public LiveData<LabTestCategories> labTestCategoriesLiveData(Activity activity) {

        labTestCategoriesMutableLiveData = new MutableLiveData<>();

        CommonUtils.showProgressDialog(activity);

        apiInterface.labTestCategories().enqueue(new Callback<LabTestCategories>() {
            @Override
            public void onResponse(@NonNull Call<LabTestCategories> call, Response<LabTestCategories> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("0")) {
                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        labTestCategoriesMutableLiveData.postValue(response.body());
                    }

                } else {
                    Toast.makeText(activity, "Services not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LabTestCategories> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return labTestCategoriesMutableLiveData;

    }


    private MutableLiveData<DoctorModelRoot> getDoctorsAsPerSpecialityMutableLiveData;

    public LiveData<DoctorModelRoot> getDoctorsAsPerSpecialityLiveData(Activity activity, String dr_speciality, String latitude, String longitude) {

        getDoctorsAsPerSpecialityMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.getDoctorsAsPerSpeciality(dr_speciality, latitude, longitude).enqueue(new Callback<DoctorModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<DoctorModelRoot> call, Response<DoctorModelRoot> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    if (response.body().getSuccess().equals("0")) {

                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        getDoctorsAsPerSpecialityMutableLiveData.postValue(response.body());

                    }

                } else {

                    Toast.makeText(activity, "Technical error", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<DoctorModelRoot> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return getDoctorsAsPerSpecialityMutableLiveData;

    }


    private MutableLiveData<DoctorModelRoot> getFilterDoctorsDoctorsAsPerSpecialityMutableLiveData;

    public LiveData<DoctorModelRoot> getFilterDoctorsAsPerSpecialityLiveData(Activity activity, String dr_speciality, String common, String clinic_name, String gender, String language, String latitude, String longitude, String consultationType, String price) {

        getFilterDoctorsDoctorsAsPerSpecialityMutableLiveData = new MutableLiveData<>();

        CommonUtils.showProgressDialog(activity);

        apiInterface.getFilterDoctors(dr_speciality, common, clinic_name, gender, language, latitude, longitude, consultationType, price).enqueue(new Callback<DoctorModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<DoctorModelRoot> call, Response<DoctorModelRoot> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    getFilterDoctorsDoctorsAsPerSpecialityMutableLiveData.postValue(response.body());

                } else {

                    Toast.makeText(activity, "Technical error", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<DoctorModelRoot> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return getFilterDoctorsDoctorsAsPerSpecialityMutableLiveData;

    }


    public MutableLiveData<TimeSlotsModelRoot> GetDoctorSlotsMutableLiveData;

    public LiveData<TimeSlotsModelRoot> GetDoctorSlotsLiveData(Activity activity, String availableDate, String doctor_Id) {

        GetDoctorSlotsMutableLiveData = new MutableLiveData<>();

        apiInterface.getDoctorSlots(availableDate, doctor_Id).enqueue(new Callback<TimeSlotsModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<TimeSlotsModelRoot> call, Response<TimeSlotsModelRoot> response) {
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("0")) {
                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        GetDoctorSlotsMutableLiveData.postValue(response.body());
                    }
                } else {
                    Toast.makeText(activity, "Technical error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TimeSlotsModelRoot> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return GetDoctorSlotsMutableLiveData;
    }


    public MutableLiveData<TimeSlotsModelRoot> getlabAvailabilityTimeSlots;

    public LiveData<TimeSlotsModelRoot> getlabAvailabilityTimeSlot(Activity activity, String doctor_Id, String availableDate) {

        getlabAvailabilityTimeSlots = new MutableLiveData<>();

        apiInterface.getlabAvailabilityTimeSlots(doctor_Id, availableDate).enqueue(new Callback<TimeSlotsModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<TimeSlotsModelRoot> call, Response<TimeSlotsModelRoot> response) {
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("0")) {
                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        getlabAvailabilityTimeSlots.postValue(response.body());
                    }
                } else {
                    Toast.makeText(activity, "Technical error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TimeSlotsModelRoot> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return getlabAvailabilityTimeSlots;
    }


    private MutableLiveData<WalletAmountModal> walletAmountModalMutableLiveData;

    public LiveData<WalletAmountModal> walletAmountModalLiveData(Activity activity, String userId) {
        CommonUtils.showProgressDialog(activity);
        walletAmountModalMutableLiveData = new MutableLiveData<>();

        apiInterface.getUserWallet(userId).enqueue(new Callback<WalletAmountModal>() {
            @Override
            public void onResponse(@NonNull Call<WalletAmountModal> call, Response<WalletAmountModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("0")) {
                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        walletAmountModalMutableLiveData.postValue(response.body());
                    }
                } else {
                    Toast.makeText(activity, "Technical error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WalletAmountModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return walletAmountModalMutableLiveData;
    }


    private MutableLiveData<GenerateOrderIdModel> genOrderId;

    public LiveData<GenerateOrderIdModel> generateOrderId(final Activity activity, String amount) {

        genOrderId = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.generateOrderId(amount).enqueue(new Callback<GenerateOrderIdModel>() {
            @Override
            public void onResponse(Call<GenerateOrderIdModel> call, Response<GenerateOrderIdModel> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    genOrderId.postValue(response.body());
                } else {
                    Toast.makeText(activity, " body null ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenerateOrderIdModel> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return genOrderId;
    }


    private MutableLiveData<WalletAmountModal> emergencyPaymentModalMutableLiveData;

    public LiveData<WalletAmountModal> AddWalletAmountModalLiveData(Activity activity, String userId, String amount, String razorpay_order_id, String razorpay_payment_id, String razorpay_signature) {

        emergencyPaymentModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.addUserWallet(userId, amount, razorpay_order_id, razorpay_payment_id, razorpay_signature).enqueue(new Callback<WalletAmountModal>() {
            @Override
            public void onResponse(@NonNull Call<WalletAmountModal> call, Response<WalletAmountModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    emergencyPaymentModalMutableLiveData.postValue(response.body());
                } else {
                    emergencyPaymentModalMutableLiveData.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<WalletAmountModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                emergencyPaymentModalMutableLiveData.postValue(null);

            }
        });

        return emergencyPaymentModalMutableLiveData;

    }


    private MutableLiveData<WalletHistoryModal> walletHistoryModalMutableLiveData;

    public LiveData<WalletHistoryModal> walletHistoryModalLiveData(Activity activity, String userId) {

        walletHistoryModalMutableLiveData = new MutableLiveData<>();
        apiInterface.userWalletHistory(userId).enqueue(new Callback<WalletHistoryModal>() {
            @Override
            public void onResponse(@NonNull Call<WalletHistoryModal> call, Response<WalletHistoryModal> response) {
                if (response.body() != null) {
                    walletHistoryModalMutableLiveData.postValue(response.body());
                } else {
                    walletHistoryModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<WalletHistoryModal> call, Throwable t) {
                walletHistoryModalMutableLiveData.postValue(null);

            }
        });

        return walletHistoryModalMutableLiveData;
    }


    private MutableLiveData<OnlineAppointmentModal> onlineAppointmentModalMutableLiveData;

    public LiveData<OnlineAppointmentModal> onlineAppointmentModalLiveData(Activity activity, String userId, String docId, String releation, String patient_gender, String patient_name, String patient_age, String patient_number, String healthProblem, String appointmentDate, String amount, String AppointmentType, String specialty, String coupanVerifiedId) {

        onlineAppointmentModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.DoctorAppointment(userId, docId, releation, patient_gender, patient_name, patient_age, patient_number, healthProblem, appointmentDate, amount, AppointmentType, specialty, coupanVerifiedId).enqueue(new Callback<OnlineAppointmentModal>() {
            @Override
            public void onResponse(@NonNull Call<OnlineAppointmentModal> call, Response<OnlineAppointmentModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    onlineAppointmentModalMutableLiveData.postValue(response.body());
                } else {
                    onlineAppointmentModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<OnlineAppointmentModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                onlineAppointmentModalMutableLiveData.postValue(null);

            }
        });

        return onlineAppointmentModalMutableLiveData;
    }


    private MutableLiveData<PendingOnlineAppointmentModal> pendingOnlineAppointmentModalMutableLiveData;

    public LiveData<PendingOnlineAppointmentModal> pendingOnlineAppointmentModalLiveData(Activity activity, String userId, String type) {
        CommonUtils.showProgressDialog(activity);
        pendingOnlineAppointmentModalMutableLiveData = new MutableLiveData<>();
        apiInterface.pendingDocAppointment(userId, type).enqueue(new Callback<PendingOnlineAppointmentModal>() {
            @Override
            public void onResponse(@NonNull Call<PendingOnlineAppointmentModal> call, Response<PendingOnlineAppointmentModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    pendingOnlineAppointmentModalMutableLiveData.postValue(response.body());
                } else {
                    pendingOnlineAppointmentModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PendingOnlineAppointmentModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                pendingOnlineAppointmentModalMutableLiveData.postValue(null);

            }
        });

        return pendingOnlineAppointmentModalMutableLiveData;
    }


    private MutableLiveData<CancelOnlineAppointment> cancelOnlineAppointmentMutableLiveData;

    public LiveData<CancelOnlineAppointment> cancelOnlineAppointmentLiveData(Activity activity, String userId, String appointmentId, String AppointmentType) {

        CommonUtils.showProgressDialog(activity);
        cancelOnlineAppointmentMutableLiveData = new MutableLiveData<>();
        apiInterface.appointmentCancelByUser(userId, appointmentId, AppointmentType).enqueue(new Callback<CancelOnlineAppointment>() {
            @Override
            public void onResponse(@NonNull Call<CancelOnlineAppointment> call, Response<CancelOnlineAppointment> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    cancelOnlineAppointmentMutableLiveData.postValue(response.body());
                } else {
                    cancelOnlineAppointmentMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<CancelOnlineAppointment> call, Throwable t) {
                CommonUtils.dismissDialog();
                cancelOnlineAppointmentMutableLiveData.postValue(null);

            }
        });

        return cancelOnlineAppointmentMutableLiveData;
    }


    private MutableLiveData<OnlineAppointmentCouponModal> onlineAppointmentCouponModalMutableLiveData;

    public LiveData<OnlineAppointmentCouponModal> onlineAppointmentCouponModalLiveData(Activity activity, String docrId) {

        CommonUtils.showProgressDialog(activity);
        onlineAppointmentCouponModalMutableLiveData = new MutableLiveData<>();
        apiInterface.getCouponForUser(docrId).enqueue(new Callback<OnlineAppointmentCouponModal>() {
            @Override
            public void onResponse(@NonNull Call<OnlineAppointmentCouponModal> call, Response<OnlineAppointmentCouponModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    onlineAppointmentCouponModalMutableLiveData.postValue(response.body());
                } else {
                    onlineAppointmentCouponModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<OnlineAppointmentCouponModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                onlineAppointmentCouponModalMutableLiveData.postValue(null);

            }
        });

        return onlineAppointmentCouponModalMutableLiveData;
    }


    private MutableLiveData<ApplyCouponAppointment> applyCouponAppointmentMutableLiveData;

    public LiveData<ApplyCouponAppointment> applyCouponAppointmentLiveData(Activity activity, String coupon_name, String amount, String userId) {

        CommonUtils.showProgressDialog(activity);
        applyCouponAppointmentMutableLiveData = new MutableLiveData<>();
        apiInterface.reedemCoupon(coupon_name, amount, userId).enqueue(new Callback<ApplyCouponAppointment>() {
            @Override
            public void onResponse(@NonNull Call<ApplyCouponAppointment> call, Response<ApplyCouponAppointment> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    applyCouponAppointmentMutableLiveData.postValue(response.body());
                } else {
                    applyCouponAppointmentMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApplyCouponAppointment> call, Throwable t) {
                CommonUtils.dismissDialog();
                applyCouponAppointmentMutableLiveData.postValue(null);

            }
        });

        return applyCouponAppointmentMutableLiveData;
    }

    private MutableLiveData<ReScheduledAppointment> reScheduledAppointment;

    public LiveData<ReScheduledAppointment> reScheduledAppointmentLiveData(Activity activity, String userId, String appointmentDate, String appointmentId, String AppointmentType) {

        CommonUtils.showProgressDialog(activity);
        reScheduledAppointment = new MutableLiveData<>();
        apiInterface.reScheduledAppointment(userId, appointmentDate, appointmentId, AppointmentType).enqueue(new Callback<ReScheduledAppointment>() {
            @Override
            public void onResponse(@NonNull Call<ReScheduledAppointment> call, Response<ReScheduledAppointment> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    reScheduledAppointment.postValue(response.body());
                } else {
                    reScheduledAppointment.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ReScheduledAppointment> call, Throwable t) {
                CommonUtils.dismissDialog();
                reScheduledAppointment.postValue(null);

            }
        });

        return reScheduledAppointment;
    }


    private MutableLiveData<HealthProblemModal> healthProblemModalMutableLiveData;

    public LiveData<HealthProblemModal> healthProblemModalLiveData(Activity activity) {
        healthProblemModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.getAllergies().enqueue(new Callback<HealthProblemModal>() {
            @Override
            public void onResponse(@NonNull Call<HealthProblemModal> call, Response<HealthProblemModal> response) {
                CommonUtils.dismissDialog();

                if (response.body() != null) {

                    if (response.body().getSuccess().equals("0")) {

                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        healthProblemModalMutableLiveData.postValue(response.body());

                    }

                } else {

                    Toast.makeText(activity, "Services not found", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<HealthProblemModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return healthProblemModalMutableLiveData;

    }


    private MutableLiveData<PendingOnlineAppointmentModal> pendingDrAppointmentModalMutableLiveData;

    public LiveData<PendingOnlineAppointmentModal> otherAppointmentsPendingOnlineAppointmentModalLiveData(Activity activity, String userId, String type) {
        CommonUtils.showProgressDialog(activity);
        pendingDrAppointmentModalMutableLiveData = new MutableLiveData<>();
        apiInterface.otherAppointmets(userId, type).enqueue(new Callback<PendingOnlineAppointmentModal>() {
            @Override
            public void onResponse(@NonNull Call<PendingOnlineAppointmentModal> call, Response<PendingOnlineAppointmentModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    pendingDrAppointmentModalMutableLiveData.postValue(response.body());
                } else {
                    pendingDrAppointmentModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PendingOnlineAppointmentModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                pendingDrAppointmentModalMutableLiveData.postValue(null);

            }
        });

        return pendingDrAppointmentModalMutableLiveData;
    }


    private MutableLiveData<MedicineDataModal> medicineDataModalMutableLiveData;

    public LiveData<MedicineDataModal> medicineDataModalLiveData(Activity activity, String categoryId) {
        CommonUtils.showProgressDialog(activity);
        medicineDataModalMutableLiveData = new MutableLiveData<>();

        apiInterface.labTestSubCategories(categoryId).enqueue(new Callback<MedicineDataModal>() {
            @Override
            public void onResponse(@NonNull Call<MedicineDataModal> call, Response<MedicineDataModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    medicineDataModalMutableLiveData.postValue(response.body());
                } else {
                    medicineDataModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MedicineDataModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                medicineDataModalMutableLiveData.postValue(null);

            }
        });

        return medicineDataModalMutableLiveData;
    }

    private MutableLiveData<LabDetailsModal> labDetailsModalMutableLiveData;

    public LiveData<LabDetailsModal> labDetailsModalLiveData(Activity activity, String categoryId) {

        CommonUtils.showProgressDialog(activity);

        labDetailsModalMutableLiveData = new MutableLiveData<>();

        apiInterface.getLabDetails(categoryId).enqueue(new Callback<LabDetailsModal>() {
            @Override
            public void onResponse(@NonNull Call<LabDetailsModal> call, Response<LabDetailsModal> response) {
                CommonUtils.dismissDialog();

                if (response.body() != null) {
                    labDetailsModalMutableLiveData.postValue(response.body());
                } else {
                    labDetailsModalMutableLiveData.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<LabDetailsModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                labDetailsModalMutableLiveData.postValue(null);

            }
        });

        return labDetailsModalMutableLiveData;
    }

    private MutableLiveData<AddToCartModal> addToCartModalMutableLiveData;

    public LiveData<AddToCartModal> addToCartModalLiveData(Activity activity, String userId, String categoryId) {
        CommonUtils.showProgressDialog(activity);
        addToCartModalMutableLiveData = new MutableLiveData<>();

        apiInterface.addToCartLabTest(userId, categoryId).enqueue(new Callback<AddToCartModal>() {
            @Override
            public void onResponse(@NonNull Call<AddToCartModal> call, Response<AddToCartModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    addToCartModalMutableLiveData.postValue(response.body());
                } else {
                    addToCartModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddToCartModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                addToCartModalMutableLiveData.postValue(null);

            }
        });

        return addToCartModalMutableLiveData;
    }

    private MutableLiveData<AddToCartModal> removeToCartModalMutableLiveData;

    public LiveData<AddToCartModal> removeToCartModalLiveData(Activity activity, String userId, String categoryId) {
        CommonUtils.showProgressDialog(activity);
        removeToCartModalMutableLiveData = new MutableLiveData<>();

        apiInterface.removeCartDetails(userId, categoryId).enqueue(new Callback<AddToCartModal>() {
            @Override
            public void onResponse(@NonNull Call<AddToCartModal> call, Response<AddToCartModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    removeToCartModalMutableLiveData.postValue(response.body());
                } else {
                    removeToCartModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddToCartModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                removeToCartModalMutableLiveData.postValue(null);

            }
        });

        return removeToCartModalMutableLiveData;
    }


    private MutableLiveData<CountCartModal> countCartModalMutableLiveData;

    public LiveData<CountCartModal> countCartModalLiveData(Activity activity, String userId, String type) {
        countCartModalMutableLiveData = new MutableLiveData<>();

        apiInterface.getCartCountPrice(userId, type).enqueue(new Callback<CountCartModal>() {
            @Override
            public void onResponse(@NonNull Call<CountCartModal> call, Response<CountCartModal> response) {
                if (response.body() != null) {
                    countCartModalMutableLiveData.postValue(response.body());
                } else {
                    countCartModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<CountCartModal> call, Throwable t) {
                countCartModalMutableLiveData.postValue(null);

            }
        });

        return countCartModalMutableLiveData;
    }


    private MutableLiveData<GetLabCategoryModal> getLabCategoryModalMutableLiveData;

    public LiveData<GetLabCategoryModal> getLabCategoryModalLiveData(Activity activity, String labId) {
        getLabCategoryModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.getLabsCategories(labId).enqueue(new Callback<GetLabCategoryModal>() {
            @Override
            public void onResponse(@NonNull Call<GetLabCategoryModal> call, Response<GetLabCategoryModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    getLabCategoryModalMutableLiveData.postValue(response.body());
                } else {
                    getLabCategoryModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetLabCategoryModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                getLabCategoryModalMutableLiveData.postValue(null);

            }
        });

        return getLabCategoryModalMutableLiveData;
    }

    private MutableLiveData<AddCartLabModal> addCartLabModalMutableLiveData;

    public LiveData<AddCartLabModal> addCartLabModalLiveData(Activity activity, String userid, String labId) {
        addCartLabModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.addToCartUser(userid, labId).enqueue(new Callback<AddCartLabModal>() {
            @Override
            public void onResponse(@NonNull Call<AddCartLabModal> call, Response<AddCartLabModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    addCartLabModalMutableLiveData.postValue(response.body());
                } else {
                    addCartLabModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddCartLabModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                addCartLabModalMutableLiveData.postValue(null);

            }
        });

        return addCartLabModalMutableLiveData;
    }


    private MutableLiveData<AddCartLabModal> addedToCartPackagesCartLabModalMutableLiveData;

    public LiveData<AddCartLabModal> addCartPackageLabModalLiveData(Activity activity, String userid, String labId) {
        addedToCartPackagesCartLabModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.addedToCartPackages(userid, labId).enqueue(new Callback<AddCartLabModal>() {
            @Override
            public void onResponse(@NonNull Call<AddCartLabModal> call, Response<AddCartLabModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    addedToCartPackagesCartLabModalMutableLiveData.postValue(response.body());
                } else {
                    addedToCartPackagesCartLabModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddCartLabModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                addedToCartPackagesCartLabModalMutableLiveData.postValue(null);

            }
        });

        return addedToCartPackagesCartLabModalMutableLiveData;
    }


    private MutableLiveData<RemoveCartModal> removeCartModalMutableLiveData;

    public LiveData<RemoveCartModal> removeCartModalLiveData(Activity activity, String userid, String labId) {
        CommonUtils.showProgressDialog(activity);
        removeCartModalMutableLiveData = new MutableLiveData<>();

        apiInterface.removeCart(userid, labId).enqueue(new Callback<RemoveCartModal>() {
            @Override
            public void onResponse(@NonNull Call<RemoveCartModal> call, Response<RemoveCartModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    removeCartModalMutableLiveData.postValue(response.body());
                } else {
                    removeCartModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RemoveCartModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                removeCartModalMutableLiveData.postValue(null);

            }
        });

        return removeCartModalMutableLiveData;
    }


    private MutableLiveData<RemoveCartModal> removeCartPackageModalMutableLiveData;

    public LiveData<RemoveCartModal> removeCartPackageModalLiveData(Activity activity, String userid, String labId) {
        CommonUtils.showProgressDialog(activity);
        removeCartPackageModalMutableLiveData = new MutableLiveData<>();

        apiInterface.removeCart(userid, labId).enqueue(new Callback<RemoveCartModal>() {
            @Override
            public void onResponse(@NonNull Call<RemoveCartModal> call, Response<RemoveCartModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    removeCartPackageModalMutableLiveData.postValue(response.body());
                } else {
                    removeCartPackageModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RemoveCartModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                removeCartPackageModalMutableLiveData.postValue(null);

            }
        });

        return removeCartPackageModalMutableLiveData;
    }


    private MutableLiveData<OnlineAppointmentCouponModal> getLabModalMutableLiveData;

    public LiveData<OnlineAppointmentCouponModal> getLabCouponModalLiveData(Activity activity, String labId) {
        CommonUtils.showProgressDialog(activity);
        getLabModalMutableLiveData = new MutableLiveData<>();


        apiInterface.getLabCoupon(labId).enqueue(new Callback<OnlineAppointmentCouponModal>() {
            @Override
            public void onResponse(@NonNull Call<OnlineAppointmentCouponModal> call, Response<OnlineAppointmentCouponModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {

                    getLabModalMutableLiveData.postValue(response.body());
                } else {
                    getLabModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<OnlineAppointmentCouponModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                getLabModalMutableLiveData.postValue(null);

            }
        });

        return getLabModalMutableLiveData;
    }


    private MutableLiveData<ApplyCouponAppointment> labApplyCouponAppointmentMutableLiveData;

    public LiveData<ApplyCouponAppointment> labCouponAppointmentLiveData(Activity activity, String coupon_name, String amount, String userId) {

        CommonUtils.showProgressDialog(activity);
        labApplyCouponAppointmentMutableLiveData = new MutableLiveData<>();
        apiInterface.reedemCouponLabVendor(coupon_name, amount, userId).enqueue(new Callback<ApplyCouponAppointment>() {
            @Override
            public void onResponse(@NonNull Call<ApplyCouponAppointment> call, Response<ApplyCouponAppointment> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    labApplyCouponAppointmentMutableLiveData.postValue(response.body());
                } else {
                    labApplyCouponAppointmentMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApplyCouponAppointment> call, Throwable t) {
                CommonUtils.dismissDialog();
                labApplyCouponAppointmentMutableLiveData.postValue(null);

            }
        });

        return labApplyCouponAppointmentMutableLiveData;
    }

    private MutableLiveData<AddPatientDetails> addPatientDetailsMutableLiveData;

    public LiveData<AddPatientDetails> addPatientDetailsLiveData(Activity activity, String userId, String name, String phone, String pincode, String state, String city, String fullAddress, String roadName) {

        CommonUtils.showProgressDialog(activity);
        addPatientDetailsMutableLiveData = new MutableLiveData<>();
        apiInterface.userDetails(userId, name, phone, pincode, state, city, fullAddress, roadName).enqueue(new Callback<AddPatientDetails>() {
            @Override
            public void onResponse(@NonNull Call<AddPatientDetails> call, Response<AddPatientDetails> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    addPatientDetailsMutableLiveData.postValue(response.body());
                } else {
                    addPatientDetailsMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddPatientDetails> call, Throwable t) {
                CommonUtils.dismissDialog();
                addPatientDetailsMutableLiveData.postValue(null);

            }
        });

        return addPatientDetailsMutableLiveData;
    }

    private MutableLiveData<AddPatientDetails> editPatientDetailsMutableLiveData;

    public LiveData<AddPatientDetails> editPatientDetailsLiveData(Activity activity, String id, String userId, String name, String phone, String pincode, String state, String city, String fullAddress, String roadName) {

        CommonUtils.showProgressDialog(activity);
        editPatientDetailsMutableLiveData = new MutableLiveData<>();
        apiInterface.editUserDetails(id, userId, name, phone, pincode, state, city, fullAddress, roadName).enqueue(new Callback<AddPatientDetails>() {
            @Override
            public void onResponse(@NonNull Call<AddPatientDetails> call, Response<AddPatientDetails> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    editPatientDetailsMutableLiveData.postValue(response.body());
                } else {
                    editPatientDetailsMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddPatientDetails> call, Throwable t) {
                CommonUtils.dismissDialog();
                editPatientDetailsMutableLiveData.postValue(null);

            }
        });

        return editPatientDetailsMutableLiveData;
    }


    private MutableLiveData<GetPatientAddress> getPatientDetailsMutableLiveData;

    public LiveData<GetPatientAddress> getPatientDetailsLiveData(Activity activity, String userId) {

        CommonUtils.showProgressDialog(activity);
        getPatientDetailsMutableLiveData = new MutableLiveData<>();
        apiInterface.getMyAddress(userId).enqueue(new Callback<GetPatientAddress>() {
            @Override
            public void onResponse(@NonNull Call<GetPatientAddress> call, Response<GetPatientAddress> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    getPatientDetailsMutableLiveData.postValue(response.body());
                } else {
                    getPatientDetailsMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetPatientAddress> call, Throwable t) {
                CommonUtils.dismissDialog();
                getPatientDetailsMutableLiveData.postValue(null);

            }
        });

        return getPatientDetailsMutableLiveData;
    }


    private MutableLiveData<AddFamilyMember> addFamilyMemberMutableLiveData;

    public LiveData<AddFamilyMember> addFamilyMemberLiveData(Activity activity, String userId, String name, String phone, String gender, String age, String relation) {

        CommonUtils.showProgressDialog(activity);
        addFamilyMemberMutableLiveData = new MutableLiveData<>();
        apiInterface.addFamilyMember(userId, name, phone, gender, age, relation).enqueue(new Callback<AddFamilyMember>() {
            @Override
            public void onResponse(@NonNull Call<AddFamilyMember> call, Response<AddFamilyMember> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    addFamilyMemberMutableLiveData.postValue(response.body());
                } else {
                    addFamilyMemberMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddFamilyMember> call, Throwable t) {
                CommonUtils.dismissDialog();
                addFamilyMemberMutableLiveData.postValue(null);

            }
        });

        return addFamilyMemberMutableLiveData;
    }


    private MutableLiveData<GetFamilyMemberModal> getFamilyMemberModalMutableLiveData;

    public LiveData<GetFamilyMemberModal> getFamilyMemberModalLiveData(Activity activity, String userId) {

        CommonUtils.showProgressDialog(activity);
        getFamilyMemberModalMutableLiveData = new MutableLiveData<>();
        apiInterface.getFamilyMember(userId).enqueue(new Callback<GetFamilyMemberModal>() {
            @Override
            public void onResponse(@NonNull Call<GetFamilyMemberModal> call, Response<GetFamilyMemberModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    getFamilyMemberModalMutableLiveData.postValue(response.body());
                } else {
                    getFamilyMemberModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetFamilyMemberModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                getFamilyMemberModalMutableLiveData.postValue(null);

            }
        });

        return getFamilyMemberModalMutableLiveData;
    }

    private MutableLiveData<GetFamilyMemberModal> deleteFamilyMemberModalMutableLiveData;

    public LiveData<GetFamilyMemberModal> deleteFamilyMemberModalLiveData(Activity activity, String userId) {

        CommonUtils.showProgressDialog(activity);
        deleteFamilyMemberModalMutableLiveData = new MutableLiveData<>();
        apiInterface.deleteFamilyMember(userId).enqueue(new Callback<GetFamilyMemberModal>() {
            @Override
            public void onResponse(@NonNull Call<GetFamilyMemberModal> call, Response<GetFamilyMemberModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    deleteFamilyMemberModalMutableLiveData.postValue(response.body());
                } else {
                    deleteFamilyMemberModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetFamilyMemberModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                deleteFamilyMemberModalMutableLiveData.postValue(null);

            }
        });

        return deleteFamilyMemberModalMutableLiveData;
    }

    private MutableLiveData<AddFamilyMember> editFamilyMemberMutableLiveData;

    public LiveData<AddFamilyMember> editFamilyMemberLiveData(Activity activity, String id, String userId, String name, String phone, String gender, String age, String relation) {

        CommonUtils.showProgressDialog(activity);
        editFamilyMemberMutableLiveData = new MutableLiveData<>();
        apiInterface.editFamilyMember(id, userId, name, phone, gender, age, relation).enqueue(new Callback<AddFamilyMember>() {
            @Override
            public void onResponse(@NonNull Call<AddFamilyMember> call, Response<AddFamilyMember> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    editFamilyMemberMutableLiveData.postValue(response.body());
                } else {
                    editFamilyMemberMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddFamilyMember> call, Throwable t) {
                CommonUtils.dismissDialog();
                editFamilyMemberMutableLiveData.postValue(null);

            }
        });

        return editFamilyMemberMutableLiveData;
    }

    private MutableLiveData<GetPatientAddress> deleteUserAddressModalMutableLiveData;

    public LiveData<GetPatientAddress> deleteUserAddressModalLiveData(Activity activity, String userId) {

        CommonUtils.showProgressDialog(activity);
        deleteUserAddressModalMutableLiveData = new MutableLiveData<>();
        apiInterface.deleteUserAddress(userId).enqueue(new Callback<GetPatientAddress>() {
            @Override
            public void onResponse(@NonNull Call<GetPatientAddress> call, Response<GetPatientAddress> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    deleteUserAddressModalMutableLiveData.postValue(response.body());
                } else {
                    deleteUserAddressModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetPatientAddress> call, Throwable t) {
                CommonUtils.dismissDialog();
                deleteUserAddressModalMutableLiveData.postValue(null);

            }
        });

        return deleteUserAddressModalMutableLiveData;
    }


    private MutableLiveData<LabBookModal> labBookModalMutableLiveData;

    public LiveData<LabBookModal> labBookModalLiveData(Activity activity, RequestBody labId,
                                                       RequestBody userId, RequestBody patient_id,
                                                       RequestBody type, MultipartBody.Part image,
                                                       RequestBody amount, RequestBody address,
                                                       RequestBody date, RequestBody time_slot,
                                                       RequestBody homeCollection, RequestBody labTestId) {

        CommonUtils.showProgressDialog(activity);

        labBookModalMutableLiveData = new MutableLiveData<>();
        apiInterface.labTestAppointments(labId, userId, patient_id,
                type, image, amount,
                address, date, time_slot, homeCollection, labTestId).enqueue(new Callback<LabBookModal>() {
            @Override
            public void onResponse(@NonNull Call<LabBookModal> call, Response<LabBookModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    labBookModalMutableLiveData.postValue(response.body());
                } else {
                    labBookModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<LabBookModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                labBookModalMutableLiveData.postValue(null);

            }
        });

        return labBookModalMutableLiveData;
    }


    private MutableLiveData<RadiologyCategoryModal> radiologyCategoryModalMutableLiveData;

    public LiveData<RadiologyCategoryModal> radiologyCategoryModalLiveData(Activity activity) {
        radiologyCategoryModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.radioCategory().enqueue(new Callback<RadiologyCategoryModal>() {
            @Override
            public void onResponse(@NonNull Call<RadiologyCategoryModal> call, Response<RadiologyCategoryModal> response) {
                CommonUtils.dismissDialog();

                if (response.body() != null) {

                    if (response.body().getSuccess().equals("0")) {

                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        radiologyCategoryModalMutableLiveData.postValue(response.body());

                    }

                } else {

                    Toast.makeText(activity, "Services not found", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<RadiologyCategoryModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return radiologyCategoryModalMutableLiveData;

    }


    private MutableLiveData<RadiologyPackageTestModal> radiologyPackageTestModalMutableLiveData;

    public LiveData<RadiologyPackageTestModal> radiologyPackageTestModalLiveData(Activity activity, String userId) {
        radiologyPackageTestModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgressDialog(activity);
        apiInterface.getAllTestPackages(userId).enqueue(new Callback<RadiologyPackageTestModal>() {
            @Override
            public void onResponse(@NonNull Call<RadiologyPackageTestModal> call, Response<RadiologyPackageTestModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("0")) {
                        Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        radiologyPackageTestModalMutableLiveData.postValue(response.body());
                    }
                } else {
                    Toast.makeText(activity, "Services not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RadiologyPackageTestModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return radiologyPackageTestModalMutableLiveData;
    }


    private MutableLiveData<GetUserLabModal> labPackageDetailsModalMutableLiveData;

    public LiveData<GetUserLabModal> labPackageDetailsModalLiveData(Activity activity, String userId) {

        CommonUtils.showProgressDialog(activity);
        labPackageDetailsModalMutableLiveData = new MutableLiveData<>();
        apiInterface.labAddedPackage(userId).enqueue(new Callback<GetUserLabModal>() {
            @Override
            public void onResponse(@NonNull Call<GetUserLabModal> call, Response<GetUserLabModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    labPackageDetailsModalMutableLiveData.postValue(response.body());
                } else {
                    labPackageDetailsModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetUserLabModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                labPackageDetailsModalMutableLiveData.postValue(null);

            }
        });

        return labPackageDetailsModalMutableLiveData;
    }


    private MutableLiveData<AddToCartPackageModal> addToCartPackageModalMutableLiveData;

    public LiveData<AddToCartPackageModal> addToCartPackageModalLiveData(Activity activity, String userId, String packageId) {

        CommonUtils.showProgressDialog(activity);
        addToCartPackageModalMutableLiveData = new MutableLiveData<>();
        apiInterface.addToCartLabTestPackage(userId, packageId).enqueue(new Callback<AddToCartPackageModal>() {
            @Override
            public void onResponse(@NonNull Call<AddToCartPackageModal> call, Response<AddToCartPackageModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    addToCartPackageModalMutableLiveData.postValue(response.body());
                } else {
                    addToCartPackageModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddToCartPackageModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                addToCartPackageModalMutableLiveData.postValue(null);

            }
        });

        return addToCartPackageModalMutableLiveData;
    }


    private MutableLiveData<MedicineDataModal> radioSubCatModalMutableLiveData;

    public LiveData<MedicineDataModal> radioSubCatModalLiveData(Activity activity, String catId) {

        CommonUtils.showProgressDialog(activity);
        radioSubCatModalMutableLiveData = new MutableLiveData<>();
        apiInterface.radioTestSubCat(catId).enqueue(new Callback<MedicineDataModal>() {
            @Override
            public void onResponse(@NonNull Call<MedicineDataModal> call, Response<MedicineDataModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    radioSubCatModalMutableLiveData.postValue(response.body());
                } else {
                    radioSubCatModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<MedicineDataModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                radioSubCatModalMutableLiveData.postValue(null);

            }
        });

        return radioSubCatModalMutableLiveData;
    }

    private MutableLiveData<AddToCartPackageModal> addToCartRadioTestModalMutableLiveData;

    public LiveData<AddToCartPackageModal> addToCartRadioModalLiveData(Activity activity, String userId, String catId) {

        CommonUtils.showProgressDialog(activity);

        addToCartRadioTestModalMutableLiveData = new MutableLiveData<>();

        apiInterface.addToCartRadioTest(userId, catId).enqueue(new Callback<AddToCartPackageModal>() {
            @Override
            public void onResponse(@NonNull Call<AddToCartPackageModal> call, Response<AddToCartPackageModal> response) {
                CommonUtils.dismissDialog();
                if (response.body() != null) {
                    addToCartRadioTestModalMutableLiveData.postValue(response.body());
                } else {
                    addToCartRadioTestModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddToCartPackageModal> call, Throwable t) {
                CommonUtils.dismissDialog();
                addToCartRadioTestModalMutableLiveData.postValue(null);

            }
        });

        return addToCartRadioTestModalMutableLiveData;
    }
}