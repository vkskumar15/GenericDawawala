package com.example.genericdawawalauser.fragments.profiles;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
import com.example.genericdawawalauser.databinding.FragmentMyOnlineConsultBinding;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

public class MyOnlineConsultFragment extends Fragment {
    FragmentMyOnlineConsultBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyOnlineConsultBinding.inflate(inflater, container, false);

        onClickListener();
        setAdapter();

        return binding.getRoot();

    }

    private void setAdapter() {

        new ViewModalClass().pendingOnlineAppointmentModalLiveData(requireActivity(), CommonUtils.getUserId(), "0").observe(requireActivity(), new Observer<PendingOnlineAppointmentModal>() {
            @Override
            public void onChanged(PendingOnlineAppointmentModal pendingOnlineAppointmentModal) {
                if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                    PendingOnlineConsultAdapter adapter = new PendingOnlineConsultAdapter(pendingOnlineAppointmentModal.getDetails(), requireActivity(), new PendingOnlineConsultAdapter.Reschedule() {
                        @Override
                        public void reschedule(PendingOnlineAppointmentModal.Detail detail) {

                        }

                        @Override
                        public void cancel(PendingOnlineAppointmentModal.Detail detail) {

                            cancelOrder(detail);
                        }
                    });
                    binding.rvVisitsUpcoming.setAdapter(adapter);
                } else {

                    binding.tvNotFound.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void cancelOrder(PendingOnlineAppointmentModal.Detail detail) {

        Dialog delete_box = new Dialog(getContext());
        delete_box.setContentView(R.layout.confirm_dialog);
        delete_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = delete_box.getWindow();
        delete_box.setCanceledOnTouchOutside(false);
        window.setGravity(Gravity.CENTER);
        delete_box.show();

        TextView textView = delete_box.findViewById(R.id.text_warnin);
        textView.setVisibility(View.VISIBLE);
        textView.setText("If You want to cancel, you will be charged 20% of your total amount as a Penality.");

        delete_box.findViewById(R.id.yes_btn).setOnClickListener(v -> {

//                LiveData<ResponseModel> liveData = userPartViewModel.DeleteLabPackageOrderLiveData(getActivity(), labModelDetails.getId());
//
//                liveData.observe(getActivity(), new Observer<ResponseModel>() {
//                    @Override
//                    public void onChanged(ResponseModel responseModel) {
//
//                        Toast.makeText(getContext(), "" + responseModel.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        setPendingList();
//
//                        delete_box.dismiss();
//
//                    }
//                });
//
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

        binding.txtHistoryConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            }
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
        new ViewModalClass().pendingOnlineAppointmentModalLiveData(requireActivity(), CommonUtils.getUserId(), "2").observe(requireActivity(), pendingOnlineAppointmentModal -> {
            if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                PendingOnlineConsultAdapter adapter = new PendingOnlineConsultAdapter(pendingOnlineAppointmentModal.getDetails(), requireActivity(), new PendingOnlineConsultAdapter.Reschedule() {
                    @Override
                    public void reschedule(PendingOnlineAppointmentModal.Detail detail) {

                    }

                    @Override
                    public void cancel(PendingOnlineAppointmentModal.Detail detail) {

                        cancelOrder(detail);
                    }
                });
                binding.recyclerViewDoctorPrescriptions.setAdapter(adapter);
            } else {

                binding.tvNotFound.setVisibility(View.VISIBLE);

            }
        });


    }

    private void setApprovedAdapter() {

        new ViewModalClass().pendingOnlineAppointmentModalLiveData(requireActivity(), CommonUtils.getUserId(), "1").observe(requireActivity(), pendingOnlineAppointmentModal -> {
            if (pendingOnlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                PendingOnlineConsultAdapter adapter = new PendingOnlineConsultAdapter(pendingOnlineAppointmentModal.getDetails(), requireActivity(), new PendingOnlineConsultAdapter.Reschedule() {
                    @Override
                    public void reschedule(PendingOnlineAppointmentModal.Detail detail) {

                    }

                    @Override
                    public void cancel(PendingOnlineAppointmentModal.Detail detail) {

                        cancelOrder(detail);
                    }
                });
                binding.rvVisitsCurrent.setAdapter(adapter);
            } else {

                binding.tvNotFound.setVisibility(View.VISIBLE);

            }
        });
    }


}