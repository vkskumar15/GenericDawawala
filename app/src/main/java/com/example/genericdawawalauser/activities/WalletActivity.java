package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.MyWalletAdapter;
import com.example.genericdawawalauser.databinding.ActivityWalletBinding;
import com.example.genericdawawalauser.modalClass.WalletAmountModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;
import com.example.genericdawawalauser.utils.NeTWorkChange;

public class WalletActivity extends AppCompatActivity {
    private ActivityWalletBinding binding;
    NeTWorkChange neTWorkChange = new NeTWorkChange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setAdapter();

        getWalletAmount();
        onClicks();
    }

    private void getWalletAmount() {
        new ViewModalClass().walletAmountModalLiveData(WalletActivity.this, CommonUtils.getUserId()).observe(WalletActivity.this, new Observer<WalletAmountModal>() {
            @Override
            public void onChanged(WalletAmountModal walletAmountModal) {
                if (walletAmountModal.getSuccess().equalsIgnoreCase("1"))
                {

                    binding.txtTotalAmount.setText("₹ "+walletAmountModal.getDetails().getWallet());
                }
            }
        });
    }

    private void onClicks() {
        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });
    }


    private void setAdapter() {
        binding.recyclerviewCreditedAmount.setAdapter(new MyWalletAdapter(this));
    }

    public void onStart() {
        registerReceiver(neTWorkChange, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        super.onStart();
    }

    public void onStop() {
        unregisterReceiver(neTWorkChange);
        super.onStop();
    }
}