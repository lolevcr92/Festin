package com.example.android.festin.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.android.festin.R;
import com.example.android.festin.activities.ActivityCountdown;

public class CdtBroadcastService extends Service {

    private long mTimeLeft = 10000;
    private final long TIMER_STEP = 1000;
//    private IBinder mBinder = new MyBinder();
    private final String NOTIFICATION_CHANNEL_ID = "ChannelID 0";
    private final static String TAG = "CdtBroadcastService";

    public static final String COUNTDOWN_BR = "com.example.android.festin.Utils.countdown_br";

    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer countDownTimer = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Starting timer..");

        countDownTimer = new CountDownTimer(mTimeLeft,TIMER_STEP) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                Log.i(TAG, "Countdown seconds remaining" + mTimeLeft / 1000);
                bi.putExtra("CountDown", mTimeLeft);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                Intent notificationIntent = new Intent(CdtBroadcastService.this, ActivityCountdown.class);
                notificationIntent.putExtra("isRunning", false);
                PendingIntent notificationPendingIntent = PendingIntent.getActivity(CdtBroadcastService.this, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        CdtBroadcastService.this, NOTIFICATION_CHANNEL_ID);
                builder.setContentTitle("Content Title");
                builder.setContentText("Content Text");
                builder.setSmallIcon(R.drawable.ic_heart);
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                builder.setDefaults(NotificationCompat.DEFAULT_ALL);
                builder.setContentIntent(notificationPendingIntent);
                builder.setAutoCancel(true);
                notificationManager.notify(0, builder.build());

            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "OnStartCommand");
//        mTimeLeft = (long) intent.getExtras().get("timeLeft");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "in onBind");
        return null;
    }

    @Override
    public void onDestroy() {
//        countDownTimer.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }
}
