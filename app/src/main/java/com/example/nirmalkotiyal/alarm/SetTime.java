package com.example.nirmalkotiyal.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SetTime extends AppCompatActivity {

    int year;
    int month;
    int day_of_month;
    TimePicker timePicker;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        year = getIntent().getIntExtra("year",0);
        month = getIntent().getIntExtra("month",0);
        day_of_month = getIntent().getIntExtra("day_of_month",0);

        Log.d("ondate", String.valueOf(year));
        Log.d("ondate", String.valueOf(month));
        Log.d("ondate", String.valueOf(day_of_month));

        askpermisssionbattery();

        timePicker = findViewById(R.id.timepicker);

        button = findViewById(R.id.set_time);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(
                    year,
                    month,
                    day_of_month,
                    timePicker.getHour(),
                    timePicker.getMinute(),
                            0
                    );
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Log.d("onclicktime", String.valueOf(timePicker.getHour()));
                    Log.d("onclicktime", String.valueOf(timePicker.getMinute()));
                    Log.d("onclicktime", String.valueOf(calendar.getTimeInMillis()));
                }
                setalarm(calendar.getTimeInMillis());
                Toast.makeText(SetTime.this,"Your Reminder Has Been Set Close App Now",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void askpermisssionbattery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = this.getPackageName();
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("package:" + packageName));
                this.startActivity(intent);
            }
        }
    }

    private void setalarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent =new Intent(this,alarm.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,0,intent,0);
        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent);
    }
}
