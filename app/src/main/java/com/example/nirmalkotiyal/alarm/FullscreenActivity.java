package com.example.nirmalkotiyal.alarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Calendar;

public class FullscreenActivity extends AppCompatActivity {

    Calendar now =Calendar.getInstance();
    MediaPlayer player;
    Vibrator vibrator;
    long[] pattern = {0, 500, 1000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Log.d("onclicktime","fullscreenactivity");
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();

        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        player = MediaPlayer.create(getApplicationContext(),R.raw.ringtone);
        player.setLooping(true);
        player.start();
        vibrator.vibrate(pattern,0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            vibrator.vibrate(VibrationEffect.createWaveform(pattern,1));
//        }
//        else{
//            vibrator.vibrate(1000);
//        }

        findViewById(R.id.not_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
                now.add(Calendar.DAY_OF_MONTH,1);
                Calendar calendar = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH),
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE)
                            ,
                            0
                    );
                    Log.d("onclicktime", String.valueOf(now.get(Calendar.HOUR_OF_DAY)));
                    Log.d("onclicktime", String.valueOf(now.get(Calendar.MINUTE)));
                    Log.d("onclicktime", String.valueOf(now.getTimeInMillis()));
                    Log.d("onclicktime", String.valueOf(now.get(Calendar.DAY_OF_MONTH)));
                }
                setalarm(now.getTimeInMillis());
                player.stop();
                Toast.makeText(FullscreenActivity.this,"You Can close App Now",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            vibrator.cancel();
                now.add(Calendar.DAY_OF_MONTH,26);
                Calendar calendar = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH),
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE)
                            ,
                            0
                    );
                    Log.d("onclicktime", String.valueOf(now.get(Calendar.HOUR_OF_DAY)));
                    Log.d("onclicktime", String.valueOf(now.get(Calendar.MINUTE)));
                    Log.d("onclicktime", String.valueOf(now.getTimeInMillis()));
                    Log.d("onclicktime", String.valueOf(now.get(Calendar.DAY_OF_MONTH)));
                }
                setalarm(now.getTimeInMillis());
            player.stop();
                Toast.makeText(FullscreenActivity.this,"You Can close App Now",Toast.LENGTH_LONG).show();

            }
        });

        findViewById(R.id.already_got_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onrecieve","asdfsadf");
                player.stop();
                vibrator.cancel();
                Intent intent = new Intent(FullscreenActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
    private void setalarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent =new Intent(this,alarm.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,0,intent,0);
        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent);
    }
}
