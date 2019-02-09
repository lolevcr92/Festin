package com.example.android.festin.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.festin.R;
import com.example.android.festin.Utils.CdtBroadcastService;

public class ActivityCountdown extends AppCompatActivity {
    private TextView countdownText;
    private TextView orderIdText;
    private long mTimeLeft;
    private boolean isRunning;

    private final String TAG = "Activity Countdown: ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_countdown);

        countdownText = findViewById(R.id.order_countdown);
        orderIdText = findViewById(R.id.order_id);

        orderIdText.setText(""+ (int)Math.random()*1000);
        Log.i(TAG, "before settext");

        if(getIntent().getExtras() != null){
            isRunning = false;
            Log.i(TAG, "Inside intent");
        } else {
            isRunning = true;
        }
        if(isRunning){
            startService(new Intent(this, CdtBroadcastService.class));
        } else {
            countdownText.setText("Pick up your order");
        }
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null){
                mTimeLeft = intent.getLongExtra("CountDown", 0);
            }

            countdownText.setText(updateUI());
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(CdtBroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broadcast receiver");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broadcast receiver");
    }

        @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e){

        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public String updateUI (){
        int minutes = (int) mTimeLeft / 60000;
        int seconds = (int) mTimeLeft % 60000 / 1000;

        String timeLeft = "" + minutes + ":";
        if(seconds < 10) timeLeft += "0";
        timeLeft += seconds;

        return timeLeft;
    }
}
