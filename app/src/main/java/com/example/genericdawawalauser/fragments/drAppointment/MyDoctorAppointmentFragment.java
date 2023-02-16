package com.example.genericdawawalauser.fragments.drAppointment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.PendingOnlineConsultAdapter;
import com.example.genericdawawalauser.databinding.FragmentMyDoctorAppointmentBinding;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.CancelOnlineAppointment;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;


public class MyDoctorAppointmentFragment extends Fragment {
    FragmentMyDoctorAppointmentBinding binding;
    PendingOnlineConsultAdapter adapter;
    public static String appointmentSlot, appointmentType, appointmentDateToShow, appointmentDateToSend;
    // 0 for pending appointment
    // 1 for approved appointment
    // 2 for history appointment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyDoctorAppointmentBinding.inflate(inflater, container, false);

        onClickListener();
        setAdapter();


        return binding.getRoot();

    }

    private void setAdapter() {
        new ViewModalClass().otherAppointmentsPendingOnlineAppointmentModalLiveData(requireActivity(), CommonUtils.getUserId(), "0").observe(requireActivity(), pendingOnlineAppointmentModal -> {
            if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                adapter = new PendingOnlineConsultAdapter(pendingOnlineAppointmentModal.getDetails(), requireActivity(), new PendingOnlineConsultAdapter.Reschedule() {
                    @Override
                    public void reschedule(PendingOnlineAppointmentModal.Detail detail) {

                        if (detail.getReScheduledCounts().equals("2")) {
                            Toast.makeText(requireActivity(), "Now you can't ReScheduled your appointment", Toast.LENGTH_SHORT).show();
                        } else {
                            Fragment fragment = new Fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", detail.getDocId());
                            bundle.putString("status", "1");
                            bundle.putString("appointmentId", detail.getAppointmentId());
                            bundle.putString("name", detail.getDocName());
                            bundle.putString("AppointmentType", detail.getAppointmentType());
                            bundle.putString("image", detail.getDoctorImage());
                            bundle.putString("amount", detail.getAmount());
                            fragment.setArguments(bundle);

                            Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment2, bundle);
                        }

                    }

                    @Override
                    public void cancel(PendingOnlineAppointmentModal.Detail detail, int pos) {

                        cancelOrder(detail, pendingOnlineAppointmentModal, pos);
                    }
                }, new PendingOnlineConsultAdapter.DownLoad() {
                    @Override
                    public void downLoad(PendingOnlineAppointmentModal.Detail detail) {

                    }
                }, new PendingOnlineConsultAdapter.ViewPrescription() {
                    @Override
                    public void viewPrescription(PendingOnlineAppointmentModal.Detail detail) {

                    }
                });
                binding.rvVisitsUpcoming.setAdapter(adapter);
            } else {
                Toast.makeText(requireActivity(), "" + pendingOnlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void cancelOrder(PendingOnlineAppointmentModal.Detail detail, PendingOnlineAppointmentModal pendingOnlineAppointmentModal, int pos) {

        Dialog delete_box = new Dialog(getContext());
        delete_box.setContentView(R.layout.confirm_dialog);
        delete_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = delete_box.getWindow();
        delete_box.setCanceledOnTouchOutside(false);
        window.setGravity(Gravity.CENTER);
        delete_box.show();

        appointmentType = detail.getAppointmentType();

        Toast.makeText(requireActivity(), "" + appointmentType, Toast.LENGTH_SHORT).show();

        TextView textView = delete_box.findViewById(R.id.text_warnin);
        textView.setVisibility(View.VISIBLE);
        textView.setText("If You want to cancel, you will be charged 20% of your total amount as a Penality.");

        delete_box.findViewById(R.id.yes_btn).setOnClickListener(v -> {

            new ViewModalClass().cancelOnlineAppointmentLiveData(requireActivity(), CommonUtils.getUserId(), detail.getAppointmentId(), appointmentType).observe(requireActivity(), new Observer<CancelOnlineAppointment>() {
                @Override
                public void onChanged(CancelOnlineAppointment cancelOnlineAppointment) {
                    if (cancelOnlineAppointment.getSuccess().equalsIgnoreCase("1")) {

                        Toast.makeText(requireActivity(), "" + cancelOnlineAppointment.getMessage(), Toast.LENGTH_SHORT).show();

                        pendingOnlineAppointmentModal.getDetails().remove(pos);

                        delete_box.dismiss();

                    } else {
                        Toast.makeText(requireActivity(), "" + cancelOnlineAppointment.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        });

        delete_box.findViewById(R.id.no_btn).setOnClickListener(v -> delete_box.dismiss());

    }

    private void onClickListener() {

        binding.txtUpcomingVisits.setOnClickListener(v -> {

            binding.recyclerViewDoctorPrescriptions.setVisibility(View.GONE);
            binding.rvVisitsUpcoming.setVisibility(View.VISIBLE);
            binding.rvVisitsCurrent.setVisibility(View.GONE);

            binding.txtUpcomingVisits.setTextColor(Color.WHITE);
            binding.txtHistoryConsultation.setTextColor(Color.BLACK);
            binding.txtCurrentVisit.setTextColor(Color.BLACK);

            binding.txtUpcomingVisits.setBackgroundResource(R.drawable.bg_left_tab_upcoming_green);
            binding.txtCurrentVisit.setBackgroundResource(R.drawable.bg_tab_current_white);
            binding.txtHistoryConsultation.setBackgroundResource(R.drawable.bg_right_tab_read);

            setAdapter();
        });

        binding.txtHistoryConsultation.setOnClickListener(v -> {

            binding.recyclerViewDoctorPrescriptions.setVisibility(View.VISIBLE);
            binding.rvVisitsUpcoming.setVisibility(View.GONE);
            binding.rvVisitsCurrent.setVisibility(View.GONE);

            binding.txtUpcomingVisits.setTextColor(Color.BLACK);
            binding.txtHistoryConsultation.setTextColor(Color.WHITE);
            binding.txtCurrentVisit.setTextColor(Color.BLACK);

            binding.txtUpcomingVisits.setBackgroundResource(R.drawable.bg_left_tab_upcoming_white);
            binding.txtCurrentVisit.setBackgroundResource(R.drawable.bg_tab_current_white);
            binding.txtHistoryConsultation.setBackgroundResource(R.drawable.bg_right_tab_read_green);

            setHistoryAdapter();

        });


        binding.txtCurrentVisit.setOnClickListener(v -> {

            binding.recyclerViewDoctorPrescriptions.setVisibility(View.GONE);
            binding.rvVisitsUpcoming.setVisibility(View.GONE);
            binding.rvVisitsCurrent.setVisibility(View.VISIBLE);

            binding.txtUpcomingVisits.setTextColor(Color.BLACK);
            binding.txtHistoryConsultation.setTextColor(Color.BLACK);
            binding.txtCurrentVisit.setTextColor(Color.WHITE);

            binding.txtUpcomingVisits.setBackgroundResource(R.drawable.bg_left_tab_upcoming_white);
            binding.txtCurrentVisit.setBackgroundResource(R.drawable.current_order);
            binding.txtHistoryConsultation.setBackgroundResource(R.drawable.bg_right_tab_read);

            setApprovedAdapter();
        });


        binding.back.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void setHistoryAdapter() {
        new ViewModalClass().otherAppointmentsPendingOnlineAppointmentModalLiveData(requireActivity(), CommonUtils.getUserId(), "2").observe(requireActivity(), pendingOnlineAppointmentModal -> {
            if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                adapter = new PendingOnlineConsultAdapter(pendingOnlineAppointmentModal.getDetails(), requireActivity(), new PendingOnlineConsultAdapter.Reschedule() {
                    @Override
                    public void reschedule(PendingOnlineAppointmentModal.Detail detail) {

                    }

                    @Override
                    public void cancel(PendingOnlineAppointmentModal.Detail detail, int pos) {


                    }
                },  new PendingOnlineConsultAdapter.DownLoad() {
                    @Override
                    public void downLoad(PendingOnlineAppointmentModal.Detail detail) {

                    }
                }, new PendingOnlineConsultAdapter.ViewPrescription() {
                    @Override
                    public void viewPrescription(PendingOnlineAppointmentModal.Detail detail) {

                    }
                } );
                binding.recyclerViewDoctorPrescriptions.setAdapter(adapter);
            } else if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("0")) {


                Toast.makeText(requireActivity(), "" + pendingOnlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void setApprovedAdapter() {
        new ViewModalClass().otherAppointmentsPendingOnlineAppointmentModalLiveData(requireActivity(), CommonUtils.getUserId(), "1").observe(requireActivity(), pendingOnlineAppointmentModal -> {
            if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                PendingOnlineConsultAdapter adapter = new PendingOnlineConsultAdapter(pendingOnlineAppointmentModal.getDetails(), requireActivity(), new PendingOnlineConsultAdapter.Reschedule() {
                    @Override
                    public void reschedule(PendingOnlineAppointmentModal.Detail detail) {

                    }

                    @Override
                    public void cancel(PendingOnlineAppointmentModal.Detail detail, int pos) {

                    }
                }, new PendingOnlineConsultAdapter.DownLoad() {
                    @Override
                    public void downLoad(PendingOnlineAppointmentModal.Detail detail) {

                    }
                }, new PendingOnlineConsultAdapter.ViewPrescription() {
                    @Override
                    public void viewPrescription(PendingOnlineAppointmentModal.Detail detail) {

                    }
                });
                binding.rvVisitsCurrent.setAdapter(adapter);
            } else {

                Toast.makeText(requireActivity(), "" + pendingOnlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}