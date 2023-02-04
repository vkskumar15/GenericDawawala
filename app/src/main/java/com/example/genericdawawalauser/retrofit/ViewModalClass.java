package com.example.genericdawawalauser.retrofit;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.genericdawawalauser.modalClass.ChangePasswordModal;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.modalClass.GenerateOrderIdModel;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;
import com.example.genericdawawalauser.modalClass.RegisterModelRoot;
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
    ApiInterface apiInterface = ((ApiInterface) BaseUrlRetrofit.getRetrofit().create(ApiInterface.class));
    /* access modifiers changed from: private */
    public MutableLiveData<RegisterModelRoot> registerModelRootMutableLiveData;
    /* access modifiers changed from: private */
    public MutableLiveData<RegisterModelRoot> registerModelRootMutableLiveData2;
    /* access modifiers changed from: private */
    public MutableLiveData<UniqueAPiModel> uniqueAPiModelMutableLiveData;
    /* access modifiers changed from: private */
    public MutableLiveData<RegisterModelRoot> updateUserDataModelRootMutableLiveData;
    /* access modifiers changed from: private */
    public MutableLiveData<ChangePasswordModal> updateUserEmailPhoneModelMutableLiveData;
    /* access modifiers changed from: private */
    public MutableLiveData<UpdateUserPhoneModel> updateUserPhoneModelMutableLiveData;

    public LiveData<UniqueAPiModel> uniqueAPiModelLiveData(final Activity activity, String email, String phone) {
        this.uniqueAPiModelMutableLiveData = new MutableLiveData<>();
        this.apiInterface.checkEmailPhone(email, phone).enqueue(new Callback<UniqueAPiModel>() {
            public void onResponse(Call<UniqueAPiModel> call, Response<UniqueAPiModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.uniqueAPiModelMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<UniqueAPiModel> call, Throwable t) {
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.uniqueAPiModelMutableLiveData;
    }

    public LiveData<RegisterModelRoot> registerModelRootLiveData(final Activity activity, String user, String email, String phone, String password, String reg_id, String device_id, String login_type, String latitude, String longitude, String chatId) {
        Activity activity2 = activity;
        this.registerModelRootMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        this.apiInterface.registerUser(user, email, phone, password, reg_id, device_id, login_type, latitude, longitude, chatId).enqueue(new Callback<RegisterModelRoot>() {
            public void onResponse(Call<RegisterModelRoot> call, Response<RegisterModelRoot> response) {
                CommonUtils.dismissProgress();
                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.registerModelRootMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<RegisterModelRoot> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.registerModelRootMutableLiveData;
    }

    public LiveData<RegisterModelRoot> loginUserModelRootLiveData(Activity activity, String emailPhone, String password, String reg_id, String device_type, String device_id, String latitude, String longitude) {
        this.registerModelRootMutableLiveData2 = new MutableLiveData<>();
        Activity activity2 = activity;
        CommonUtils.showProgress(activity, "Loading....");
        this.apiInterface.loginUser(emailPhone, password, reg_id, device_type, device_id, latitude, longitude).enqueue(new Callback<RegisterModelRoot>() {
            public void onResponse(Call<RegisterModelRoot> call, Response<RegisterModelRoot> response) {
                CommonUtils.dismissProgress();
                if (response.body() != null) {
                    ViewModalClass.this.registerModelRootMutableLiveData2.postValue(response.body());
                }
            }

            public void onFailure(Call<RegisterModelRoot> call, Throwable t) {
                CommonUtils.dismissProgress();
                ViewModalClass.this.registerModelRootMutableLiveData2.postValue(null);
            }
        });
        return this.registerModelRootMutableLiveData2;
    }

    public LiveData<UpdateUserPhoneModel> updateUserPhoneModelLiveData(final Activity activity, String emailPhone) {
        this.updateUserPhoneModelMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        this.apiInterface.phoneEmailVerification(emailPhone).enqueue(new Callback<UpdateUserPhoneModel>() {
            public void onResponse(Call<UpdateUserPhoneModel> call, Response<UpdateUserPhoneModel> response) {
                CommonUtils.dismissProgress();
                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.updateUserPhoneModelMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<UpdateUserPhoneModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.updateUserPhoneModelMutableLiveData;
    }

    public LiveData<ChangePasswordModal> changePasswordModalLiveData(final Activity activity, String password) {
        this.updateUserEmailPhoneModelMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        this.apiInterface.updatePhoneEmail(password).enqueue(new Callback<ChangePasswordModal>() {
            public void onResponse(Call<ChangePasswordModal> call, Response<ChangePasswordModal> response) {
                CommonUtils.dismissProgress();
                if (response.body().getSuccess().equalsIgnoreCase("0")) {
                    Toast.makeText(activity, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.body() != null) {
                    ViewModalClass.this.updateUserEmailPhoneModelMutableLiveData.postValue(response.body());
                }
            }

            public void onFailure(Call<ChangePasswordModal> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return this.updateUserEmailPhoneModelMutableLiveData;
    }

    public LiveData<RegisterModelRoot> updateUserDataModelRootLiveData(final Activity activity, RequestBody userId, RequestBody username, RequestBody gender, RequestBody email, RequestBody dob, RequestBody phone, RequestBody address, MultipartBody.Part image) {
        Activity activity2 = activity;
        this.updateUserDataModelRootMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        this.apiInterface.updateUserData(userId, username, gender, email, dob, phone, address, image).enqueue(new Callback<RegisterModelRoot>() {
            public void onResponse(Call<RegisterModelRoot> call, Response<RegisterModelRoot> response) {
                CommonUtils.dismissProgress();
                if (response.body() == null) {
                    CommonUtils.dismissProgress();
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
        CommonUtils.showProgress(activity, "Loading....");
        apiInterface.getDoctorSpecialities().enqueue(new Callback<DoctorModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<DoctorModelRoot> call, Response<DoctorModelRoot> response) {
                CommonUtils.dismissProgress();

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
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return GetDoctorSpecialitiesMutableLiveData;

    }


    private MutableLiveData<DoctorModelRoot> getDoctorsAsPerSpecialityMutableLiveData;

    public LiveData<DoctorModelRoot> getDoctorsAsPerSpecialityLiveData(Activity activity, String dr_speciality, String latitude, String longitude) {

        getDoctorsAsPerSpecialityMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        apiInterface.getDoctorsAsPerSpeciality(dr_speciality, latitude, longitude).enqueue(new Callback<DoctorModelRoot>() {
            @Override
            public void onResponse(@NonNull Call<DoctorModelRoot> call, Response<DoctorModelRoot> response) {
                CommonUtils.dismissProgress();
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
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return getDoctorsAsPerSpecialityMutableLiveData;

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


    private MutableLiveData<WalletAmountModal> walletAmountModalMutableLiveData;

    public LiveData<WalletAmountModal> walletAmountModalLiveData(Activity activity, String userId) {
        CommonUtils.showProgress(activity, "Loading....");
        walletAmountModalMutableLiveData = new MutableLiveData<>();

        apiInterface.getUserWallet(userId).enqueue(new Callback<WalletAmountModal>() {
            @Override
            public void onResponse(@NonNull Call<WalletAmountModal> call, Response<WalletAmountModal> response) {
                CommonUtils.dismissProgress();
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
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return walletAmountModalMutableLiveData;
    }


    private MutableLiveData<GenerateOrderIdModel> genOrderId;

    public LiveData<GenerateOrderIdModel> generateOrderId(final Activity activity, String amount) {

        genOrderId = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        apiInterface.generateOrderId(amount).enqueue(new Callback<GenerateOrderIdModel>() {
            @Override
            public void onResponse(Call<GenerateOrderIdModel> call, Response<GenerateOrderIdModel> response) {
                CommonUtils.dismissProgress();
                if (response.body() != null) {
                    genOrderId.postValue(response.body());
                } else {
                    Toast.makeText(activity, " body null ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GenerateOrderIdModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return genOrderId;
    }


    private MutableLiveData<WalletAmountModal> emergencyPaymentModalMutableLiveData;

    public LiveData<WalletAmountModal> AddWalletAmountModalLiveData(Activity activity, String userId, String amount, String razorpay_order_id, String razorpay_payment_id, String razorpay_signature) {

        emergencyPaymentModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        apiInterface.addUserWallet(userId, amount, razorpay_order_id, razorpay_payment_id, razorpay_signature).enqueue(new Callback<WalletAmountModal>() {
            @Override
            public void onResponse(@NonNull Call<WalletAmountModal> call, Response<WalletAmountModal> response) {
                CommonUtils.dismissProgress();
                if (response.body() != null) {
                    emergencyPaymentModalMutableLiveData.postValue(response.body());
                } else {
                    emergencyPaymentModalMutableLiveData.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<WalletAmountModal> call, Throwable t) {
                CommonUtils.dismissProgress();
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

    public LiveData<OnlineAppointmentModal> onlineAppointmentModalLiveData(Activity activity,
                                                                           String userId, String docId, String releation, String patient_gender,
                                                                           String patient_name, String patient_age, String patient_number, String healthProblem,
                                                                           String appointmentDate, String amount) {

        onlineAppointmentModalMutableLiveData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "Loading....");
        apiInterface.DoctorAppointment(userId, docId, releation, patient_gender, patient_name, patient_age, patient_number, healthProblem, appointmentDate, amount).enqueue(new Callback<OnlineAppointmentModal>() {
            @Override
            public void onResponse(@NonNull Call<OnlineAppointmentModal> call, Response<OnlineAppointmentModal> response) {
                CommonUtils.dismissProgress();
                if (response.body() != null) {
                    onlineAppointmentModalMutableLiveData.postValue(response.body());
                } else {
                    onlineAppointmentModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<OnlineAppointmentModal> call, Throwable t) {
                CommonUtils.dismissProgress();
                onlineAppointmentModalMutableLiveData.postValue(null);

            }
        });

        return onlineAppointmentModalMutableLiveData;
    }


    private MutableLiveData<PendingOnlineAppointmentModal> pendingOnlineAppointmentModalMutableLiveData;

    public LiveData<PendingOnlineAppointmentModal> pendingOnlineAppointmentModalLiveData(Activity activity, String userId) {
        CommonUtils.showProgress(activity, "Loading....");
        pendingOnlineAppointmentModalMutableLiveData = new MutableLiveData<>();
        apiInterface.pendingDocAppointment(userId).enqueue(new Callback<PendingOnlineAppointmentModal>() {
            @Override
            public void onResponse(@NonNull Call<PendingOnlineAppointmentModal> call, Response<PendingOnlineAppointmentModal> response) {
                CommonUtils.dismissProgress();
                if (response.body() != null) {
                    pendingOnlineAppointmentModalMutableLiveData.postValue(response.body());
                } else {
                    pendingOnlineAppointmentModalMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PendingOnlineAppointmentModal> call, Throwable t) {
                CommonUtils.dismissProgress();
                pendingOnlineAppointmentModalMutableLiveData.postValue(null);

            }
        });

        return pendingOnlineAppointmentModalMutableLiveData;
    }

}