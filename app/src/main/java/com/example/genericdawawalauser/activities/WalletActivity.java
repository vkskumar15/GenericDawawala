package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.MyWalletAdapter;
import com.example.genericdawawalauser.databinding.ActivityWalletBinding;
import com.example.genericdawawalauser.modalClass.GenerateOrderIdModel;
import com.example.genericdawawalauser.modalClass.WalletAmountModal;
import com.example.genericdawawalauser.modalClass.WalletHistoryModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.example.genericdawawalauser.utils.CommonUtils;
import com.example.genericdawawalauser.utils.NeTWorkChange;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class WalletActivity extends AppCompatActivity implements PaymentResultWithDataListener {
    private ActivityWalletBinding binding;
    NeTWorkChange neTWorkChange = new NeTWorkChange();
    String razorPayKey, orderIdRazor;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getWalletAmount();
        onClicks();

        setAdapter();
    }

    private void getWalletAmount() {
        new ViewModalClass().walletAmountModalLiveData(WalletActivity.this,
                CommonUtils.getUserId()).observe(WalletActivity.this, new Observer<WalletAmountModal>() {
            @Override
            public void onChanged(WalletAmountModal walletAmountModal) {
                if (walletAmountModal.getSuccess().equalsIgnoreCase("1")) {

                    binding.txtTotalAmount.setText("₹ " + walletAmountModal.getDetails().getWallet());
                }
            }
        });
    }

    private void onClicks() {
        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnTapToWithdraw.setOnClickListener(v -> {

            amount = binding.amount.getText().toString();

            if (amount.isEmpty()) {
                Toast.makeText(this, "Enter Amount", Toast.LENGTH_SHORT).show();

            } else {

                generateOrderId(amount);

            }
        });
    }

    private void generateOrderId(String strPrice) {
        new ViewModalClass().generateOrderId(WalletActivity.this, strPrice).observe(this, new Observer<GenerateOrderIdModel>() {
            @Override
            public void onChanged(GenerateOrderIdModel generateOrderIdModel) {
                if (generateOrderIdModel.getSuccess().equalsIgnoreCase("1")) {

                    razorPayKey = generateOrderIdModel.getKey();
                    orderIdRazor = generateOrderIdModel.getOrderId();

                    startPayment(razorPayKey, orderIdRazor, strPrice);

                } else {
                    Toast.makeText(WalletActivity.this, "" + generateOrderIdModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startPayment(String razorPayKey, String orderId, String amount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID(razorPayKey);
        checkout.setImage(R.drawable.generic_dawawala_logo);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Generic Dawawala");
            options.put("order_id", orderId);
            options.put("theme.color", "#0daaed");
            options.put("currency", "INR");
            options.put("amount", amount);
            options.put("prefill.email", App.getSharedPre().getString(AppConstants.USER_EMAIL));
            options.put("prefill.contact", App.getSharedPre().getString(AppConstants.PHONE_NUMBER));
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(this, options);

        } catch (Exception e) {
            Log.e("error", "Error in starting Razorpay Checkout", e);
        }

    }

    private void setAdapter() {
        new ViewModalClass().walletHistoryModalLiveData(WalletActivity.this, CommonUtils.getUserId()).observe(WalletActivity.this,
                walletHistoryModal -> {
                    if (walletHistoryModal.getSuccess().equalsIgnoreCase("1")) {
                        MyWalletAdapter adapter = new MyWalletAdapter(WalletActivity.this, walletHistoryModal.getDetails());
                        binding.recyclerviewCreditedAmount.setAdapter(adapter);
                    } else {
                        Toast.makeText(WalletActivity.this, "" + walletHistoryModal.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void onStart() {
        registerReceiver(neTWorkChange, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        super.onStart();
    }

    public void onStop() {
        unregisterReceiver(neTWorkChange);
        super.onStop();
    }

    @Override
    public void onPaymentSuccess(String paymentId, PaymentData paymentData) {

        checkPaymentStatus(paymentData.getOrderId(), paymentId, paymentData.getSignature());

    }


    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {


    }

    private void checkPaymentStatus(String orderId, String paymentId, String signature) {

        new ViewModalClass().AddWalletAmountModalLiveData(WalletActivity.this, CommonUtils.getUserId(), amount, orderId, paymentId, signature).observe(WalletActivity.this, new Observer<WalletAmountModal>() {
            @Override
            public void onChanged(WalletAmountModal walletAmountModal) {
                if (walletAmountModal.getSuccess().equalsIgnoreCase("1")) {

                    confirmationPopUp();
                } else {
                    Toast.makeText(WalletActivity.this, "" + walletAmountModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void confirmationPopUp() {

        Dialog order_confirmation_box = new Dialog(this);
        order_confirmation_box.setContentView(R.layout.order_confirmation_dialogue_box);
        order_confirmation_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = order_confirmation_box.getWindow();
        order_confirmation_box.setCanceledOnTouchOutside(false);
        order_confirmation_box.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        order_confirmation_box.show();

        order_confirmation_box.findViewById(R.id.ok_btn).setOnClickListener(view -> {

            Intent intent = new Intent(WalletActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        });

    }
}