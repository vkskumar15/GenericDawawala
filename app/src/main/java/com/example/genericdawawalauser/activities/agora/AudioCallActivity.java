package com.example.genericdawawalauser.activities.agora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.HomeActivity;
import com.example.genericdawawalauser.activities.MainActivity;
import com.example.genericdawawalauser.databinding.ActivityAudioCallBinding;
import com.example.genericdawawalauser.modalClass.RegisterModel;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

public class AudioCallActivity extends AppCompatActivity {
    private RtcEngine mRtcEngine;
    private boolean mMuted, mSpeakerOn;
    private PowerManager.WakeLock wakeLock;
    private AudioManager audioManager;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    String appId = "7f7847f520a04e7784221a61a66d57b9";
    ActivityAudioCallBinding binding;
    private RegisterModel registerModel = new RegisterModel();
    String docName, token, docImage, docID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudioCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        registerModel.setToken(intent.getStringExtra(HomeActivity.data_key));

        docName = intent.getStringExtra("docName");
        token = App.getSingleton().getToken();
        docImage = intent.getStringExtra("docImage");
        docID = intent.getStringExtra("docID");

        Log.d("CALLINGToken","" + App.getSingleton().getToken());
        Log.d("CALLINGToken","" +  docName);

        if (registerModel != null) {

            Log.d("TAG", "onCreate: " + registerModel.getToken());

        }

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, 1)) {
            initAgoraEngineAndJoinChannel();
        }


        setData();
        senseProximity();
    }


    private void senseProximity() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(0x00000020, getLocalClassName());

        SensorEventListener sens = new SensorEventListener() {
            @SuppressLint("WakelockTimeout")
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.values[0] < mProximity.getMaximumRange()) {
                    wakeLock.acquire();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        mSensorManager.registerListener(sens, mProximity, 2 * 1000 * 1000);

    }


    private void setData() {

//        Glide.with(this).load(AppConstants.userImage).placeholder(R.drawable.default_profile).into(imgPatient);
//        txtPatientName.setText(AppConstants.userName);
      binding.chronoMeter.start();

    }


    private void initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine();
        joinChannel();
    }

    public boolean checkSelfPermission(String permission, int requestCode) {

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {

        @Override
        public void onUserJoined(int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    startCallimgTime();
                }
            });
            Log.i("onUserJoined: ", "vbjhvuyvuvuhvhj");
        }


        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });

        }

        // Listen for the onUserOffline callback.
        // This callback occurs when the remote user leaves the channel or drops offline.
        @Override
        public void onUserOffline(final int uid, final int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    onRemoteUserLeft(uid, reason);
                    finish();
                }
            });
        }

        // Listen for the onUserMuterAudio callback.
        // This callback occurs when a remote user stops sending the audio stream.
        @Override
        public void onUserMuteAudio(final int uid, final boolean muted) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    onRemoteUserVoiceMuted(uid, muted);
                }
            });
        }
    };

    private void initializeAgoraEngine() {
        try {

            RtcEngine mRtcEngine = RtcEngine.create(getBaseContext(), appId, mRtcEventHandler);
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
        } catch (Exception e) {
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    private void joinChannel() {
        initializeEngine();
        String callToken = "007eJxTYIhryimJfL9184E4wYvpHCsWy0gnBF/WCLl1KnwhU77zzYsKDOZp5hYm5mmmRgaJBiap5kCOkZFhohkQmaWYmidZlso/S24IZGRw3SvKwAiFID47Q3pqXmpRZjIDAwBmsx8r";
        mRtcEngine.joinChannel(callToken, "generic", "Extra Optional Data", 0);
    }

    private void initializeEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), appId, mRtcEventHandler);
        } catch (Exception e) {
            Log.e("agora", Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    public void onLocalAudioMuteClicked(View view) {
        mMuted = !mMuted;
        mRtcEngine.muteLocalAudioStream(mMuted);
        int res = mMuted ? R.drawable.btn_mute : R.drawable.btn_unmute;
        binding.btnMute.setImageResource(res);
    }

    public void endCall(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("End Call");
        builder1.setMessage("Are you sure you want to End Call ?");
        builder1.setIcon(R.drawable.btn_endcall_normal);
        builder1.setCancelable(false);

        builder1.setPositiveButton("End Call", (dialog, id) -> {
            finish();
            mRtcEngine.leaveChannel();
            dialog.cancel();
        });

        builder1.setNegativeButton("Dismiss", (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @SuppressLint("WrongConstant")
    public void onSpeakerClicked(View view) {
        mSpeakerOn = !mSpeakerOn;
        // Stops/Resumes sending the local audio stream.
        if (!mSpeakerOn) {
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setSpeakerphoneOn(true);
            binding.btnSpeaker.setImageResource(R.drawable.btn_speaker_normal);
        } else {
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.setMode(AudioManager.ROUTE_SPEAKER);
            audioManager.setSpeakerphoneOn(false);
            binding.btnSpeaker.setImageResource(R.drawable.btn_speaker_pressed);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("End Call");
        builder1.setMessage("End call before you exit");
        builder1.setIcon(R.drawable.warning);
        builder1.setCancelable(false);
        builder1.setPositiveButton("End Call", (dialog, id) -> {
            finish();
            mRtcEngine.leaveChannel();
            dialog.cancel();
        });

        builder1.setNegativeButton("Dismiss", (dialog, id) -> dialog.cancel());
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    @Override
    protected void onPause() {
        super.onPause();

        mRtcEngine.leaveChannel();
//        finish();
    }
}