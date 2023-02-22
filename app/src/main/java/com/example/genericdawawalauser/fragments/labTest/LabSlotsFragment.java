package com.example.genericdawawalauser.fragments.labTest;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.GridViewSelectAfternoonSlotAdapter;
import com.example.genericdawawalauser.adapters.online_consultation.GridViewSelectEveningSlotAdapter;
import com.example.genericdawawalauser.adapters.online_consultation.GridViewSelectMorningSlotAdapter;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorTimeSlotFragment;
import com.example.genericdawawalauser.fragments.onlineConsult.FinalAppointmentFragment;
import com.example.genericdawawalauser.fragments.profiles.MyOnlineConsultFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.ReScheduledAppointment;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.TimeSlotsModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LabSlotsFragment extends Fragment implements GridViewSelectMorningSlotAdapter.Select,
        GridViewSelectAfternoonSlotAdapter.Select, GridViewSelectEveningSlotAdapter.Select {
    private View view;
    RelativeLayout relativeLayout;

    ImageView back_arrow, doctorImage, morning_arrow, afterNoon_arrow, evening_arrow, icon;
    private Button button;

    private GridView gridViewMorning, gridViewAfternoon, gridViewEvening;
    private LinearLayoutCompat layout_morningGrid, layout_afterNoonGrid, layout_eveningGrid;

    int mCheck = 0, aCheck = 0, eCheck = 0;

    public static int typeCheck = 0;

    int mNum = 0, aNum = 0, eNum = 0;

    public static String doctorId = "", statusNew;

    private int mYear, mDay, mMonth;
    String onlineStatus, offlineStatus, drStatus;
    String selected_date = "", dateToSend = "";
    String docId, getDoctorId, status, appointmentId, appointmentSlot, name, image, amount, AppointmentType;

    TextView doctor_name, doctorQualificationAndSpeciality, selectDate, videoCallPrice, cunsultationTxt, offline_fee;

    public static DoctorModelDetails doctorModelDetails;

    GridViewSelectMorningSlotAdapter gridViewSelectMorningSlotAdapter;
    GridViewSelectAfternoonSlotAdapter gridViewSelectAfternoonSlotAdapter;
    GridViewSelectEveningSlotAdapter gridViewSelectEveningSlotAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lab_slots, container, false);

        findIds();

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            docId = bundle.getString("id");
            status = bundle.getString("status");
            appointmentId = bundle.getString("appointmentId");
            name = bundle.getString("name");
            image = bundle.getString("image");
            amount = bundle.getString("amount");
            drStatus = bundle.getString("drStatus");
            onlineStatus = bundle.getString("online");
            offlineStatus = bundle.getString("offline");
            AppointmentType = bundle.getString("AppointmentType");

        }


        layout_morningGrid.setVisibility(View.GONE);
        layout_afterNoonGrid.setVisibility(View.GONE);
        layout_eveningGrid.setVisibility(View.GONE);

        button.setVisibility(View.GONE);

        onClicks();

        onClicks();
//        if (status == "1") {
//            doctorId = docId;
//        } else {
//            doctorId = doctorModelDetails.getId();
//
//        }

        selectDate.setText(getDateTime());

        FinalAppointmentFragment.appointmentDateToSend = getDateTimeToSend();
        FinalAppointmentFragment.appointmentDateToShow = getDateTime();


        if (doctorModelDetails != null) {

            setData();

        }

        return view;
    }

    private void onClicks() {
        morning_arrow.setOnClickListener(v -> {

            if (mCheck == 1) {

                if (mNum % 2 == 0) {

                    layout_morningGrid.setVisibility(View.VISIBLE);
                    layout_afterNoonGrid.setVisibility(View.GONE);
                    layout_eveningGrid.setVisibility(View.GONE);

                    mNum++;

                } else {

                    layout_morningGrid.setVisibility(View.GONE);

                    mNum++;

                }

            } else {

                Toast.makeText(getContext(), "No slots Available", Toast.LENGTH_SHORT).show();

            }

        });
        afterNoon_arrow.setOnClickListener(v -> {

            if (aCheck == 1) {

                if (aNum % 2 == 0) {

                    layout_afterNoonGrid.setVisibility(View.VISIBLE);
                    layout_morningGrid.setVisibility(View.GONE);
                    layout_eveningGrid.setVisibility(View.GONE);

                    aNum++;

                } else {

                    layout_afterNoonGrid.setVisibility(View.GONE);

                    aNum++;

                }

            } else {

                Toast.makeText(getContext(), "No slots Available", Toast.LENGTH_SHORT).show();

            }

        });
        evening_arrow.setOnClickListener(v -> {

            if (eCheck == 1) {

                if (eNum % 2 == 0) {

                    layout_eveningGrid.setVisibility(View.VISIBLE);
                    layout_afterNoonGrid.setVisibility(View.GONE);
                    layout_morningGrid.setVisibility(View.GONE);

                    eNum++;

                } else {

                    layout_eveningGrid.setVisibility(View.GONE);

                    eNum++;

                }


            } else {

                Toast.makeText(getContext(), "No slots Available", Toast.LENGTH_SHORT).show();

            }
        });
        back_arrow.setOnClickListener(v -> requireActivity().onBackPressed());

        if (status == "1") {
            button.setOnClickListener(v -> {

                Log.d("asd", appointmentSlot + " " + selected_date);
                Log.d("asd", appointmentId);

                new ViewModalClass().reScheduledAppointmentLiveData(requireActivity(), CommonUtils.getUserId(),
                        appointmentSlot + " " + selected_date, appointmentId, "1").observe(requireActivity(), new Observer<ReScheduledAppointment>() {
                    @Override
                    public void onChanged(ReScheduledAppointment pendingOnlineAppointmentModal) {
                        if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                            requireActivity().onBackPressed();
                            Toast.makeText(requireActivity(), "" + pendingOnlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "" + pendingOnlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            });
        } else if (drStatus == "1") {
            button.setOnClickListener(v -> {

                FinalAppointmentFragment.doctorModelDetails = doctorModelDetails;

                App.getSingleton().setOnlinePrice(doctorModelDetails.getOnline_price());
                Navigation.findNavController(v).navigate(R.id.finalAppointmentFragment2);
            });
        } else if (drStatus == "2") {
            button.setOnClickListener(v -> {

                FinalAppointmentFragment.doctorModelDetails = doctorModelDetails;
                App.getSingleton().setOfflinePrice(doctorModelDetails.getOffline_price());
                Navigation.findNavController(v).navigate(R.id.finalAppointmentFragment2);
            });
        } else {
            button.setOnClickListener(v -> {

                FinalAppointmentFragment.doctorModelDetails = doctorModelDetails;

                statusNew = "0";
                Navigation.findNavController(v).navigate(R.id.finalAppointmentFragment);

            });
        }


        relativeLayout.setOnClickListener(v -> {

//                Navigation.findNavController(view).navigate(R.id.action_selectTimeSlot_to_videoChat);

        });

        selectDate.setOnClickListener(view -> getCalendar());

    }

    private void setData() {

        if (status == "1") {
            Glide.with(getContext()).load(doctorModelDetails.getDoctorImage()).error(R.drawable.doctor_12).into(doctorImage);
            doctor_name.setText("Dr. " + name);
            videoCallPrice.setText(amount);

        }
        if (drStatus == "1") {
            Glide.with(getContext()).load(doctorModelDetails.getDoctorImage()).error(R.drawable.doctor_12).into(doctorImage);
            doctor_name.setText("Dr. " + name);
            cunsultationTxt.setText("ONLINE CONSULTATION");
            offline_fee.setVisibility(View.GONE);
            videoCallPrice.setText(doctorModelDetails.getOnline_price());
        } else if (drStatus == "2") {
            icon.setVisibility(View.GONE);

            offline_fee.setVisibility(View.VISIBLE);
            cunsultationTxt.setText("OFFLINE CONSULTATION");
            Glide.with(getContext()).load(doctorModelDetails.getDoctorImage()).error(R.drawable.doctor_12).into(doctorImage);
            doctor_name.setText("Dr. " + name);
            videoCallPrice.setVisibility(View.GONE);
            String price = "\u20B9 " + doctorModelDetails.getOffline_price();
            offline_fee.setText(price);

        }
        Glide.with(getContext()).load(Uri.parse(doctorModelDetails.getDoctorImage())).error(R.drawable.doctor_12).into(doctorImage);
        doctor_name.setText("Dr. " + doctorModelDetails.getName());
        String specialityAndQualification = doctorModelDetails.getQualificationTitle() + " , " + doctorModelDetails.getSpecialistTitle();
        doctorQualificationAndSpeciality.setText(specialityAndQualification);
        String price = "\u20B9 " + doctorModelDetails.getOnline_price();
        videoCallPrice.setText(price);

    }

    private void findIds() {

        relativeLayout = view.findViewById(R.id.relative_select_time_3);
        offline_fee = view.findViewById(R.id.offline_fee);
//        recyclerView= view.findViewById(R.id.recycler_time_slot);
        back_arrow = view.findViewById(R.id.back);
        button = view.findViewById(R.id.btn_continue);

        back_arrow.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        doctor_name = view.findViewById(R.id.doctrName);
        doctorImage = view.findViewById(R.id.circular_image);
        doctorQualificationAndSpeciality = view.findViewById(R.id.qualificationAndSpeciality);

        gridViewMorning = view.findViewById(R.id.grid_view_morning);
        gridViewAfternoon = view.findViewById(R.id.grid_view_afternoon);
        gridViewEvening = view.findViewById(R.id.grid_view_evening);

        morning_arrow = view.findViewById(R.id.morning_slotsArrow);
        afterNoon_arrow = view.findViewById(R.id.afterNoon_slotsArrow);
        evening_arrow = view.findViewById(R.id.evening_slotsArrow);

        layout_morningGrid = view.findViewById(R.id.lay_morningGrid);
        layout_afterNoonGrid = view.findViewById(R.id.lay_afternoonGrid);
        layout_eveningGrid = view.findViewById(R.id.lay_eveningGrid);

        selectDate = view.findViewById(R.id.txt_select_date);

        videoCallPrice = view.findViewById(R.id.videoCallPriceTxt);

        icon = view.findViewById(R.id.video_icon);
        cunsultationTxt = view.findViewById(R.id.cunsultation_txt);

    }

    @Override
    public void onClick(String slot) {
        if (status == "1") {
            MyOnlineConsultFragment.appointmentSlot = slot;
            appointmentSlot = slot;
            button.setVisibility(View.VISIBLE);


        } else {
            FinalAppointmentFragment.appointmentSlot = slot;
            button.setVisibility(View.VISIBLE);
        }


    }

    private String getDateTime() {

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        Date date = new Date();

        return dateFormat.format(date);

    }

    private String getDateTimeToSend() {

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        return dateFormat.format(date);

    }

    private void getCalendar() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, i, i1, i2) -> {
            Date myDate = new Date();
            myDate.setMonth(i1);
            myDate.setYear(i - 1900);
            myDate.setDate(i2);
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String mdy = dmyFormat.format(myDate);

            dateToSend = mdy;

            FinalAppointmentFragment.appointmentDateToSend = dateToSend;
            SimpleDateFormat dmyFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
            String mdy2 = dmyFormat2.format(myDate);
            selected_date = mdy2;

            selectDate.setText(selected_date);


            getSlots(App.getSingleton().getLabId(), dateToSend);

        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        datePickerDialog.show();


    }

    public void getSlots(String id, String date) {

        if (!(id.equals("") && date.equals(""))) {

            Log.d(TAG, "getSlots: " + id);

            Log.d(TAG, "getSlots: " + date);

//            Toast.makeText(getContext(), "id and date is :- "+id+" , "+date, Toast.LENGTH_SHORT).show();

            new ViewModalClass().getlabAvailabilityTimeSlot(getActivity(),id, date).observe(getActivity(), new Observer<TimeSlotsModelRoot>() {
                @Override
                public void onChanged(TimeSlotsModelRoot timeSlotsModelRoot) {

                    if (timeSlotsModelRoot != null) {
                        try {
                            Toast.makeText(getContext(), "" + timeSlotsModelRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


//                        getSlotsList(timeSlotsModelRoot.getDetails().getSlots());

                        if (timeSlotsModelRoot.getDetails().getMorning_type().equalsIgnoreCase("1")) {

                            mCheck = 1;


                            gridViewSelectMorningSlotAdapter = new GridViewSelectMorningSlotAdapter(getContext(), timeSlotsModelRoot.getDetails().getMorning_slots(), LabSlotsFragment.this);
                            gridViewMorning.setAdapter(gridViewSelectMorningSlotAdapter);

                        }

                        if (timeSlotsModelRoot.getDetails().getAfternoon_type().equalsIgnoreCase("1")) {

                            aCheck = 1;

                            gridViewSelectAfternoonSlotAdapter = new GridViewSelectAfternoonSlotAdapter(getContext(), timeSlotsModelRoot.getDetails().getAfternoon_slots(), LabSlotsFragment.this);
                            gridViewAfternoon.setAdapter(gridViewSelectAfternoonSlotAdapter);

                        }

                        if (timeSlotsModelRoot.getDetails().getEvening_type().equalsIgnoreCase("1")) {

                            eCheck = 1;

                            gridViewSelectEveningSlotAdapter = new GridViewSelectEveningSlotAdapter(getContext(), timeSlotsModelRoot.getDetails().getEvening_slots(), LabSlotsFragment.this);
                            gridViewEvening.setAdapter(gridViewSelectEveningSlotAdapter);

                        }

                    }

                }

            });

        }

    }
}