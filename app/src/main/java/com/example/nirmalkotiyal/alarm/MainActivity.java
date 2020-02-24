package com.example.nirmalkotiyal.alarm;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity{
    Calendar set = Calendar.getInstance();
    TimePickerDialog timePickerdialog;
    DatePickerDialog datePickerDialog;
    TimePicker timePicker;
    EditText editText;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////        askpermisssionbattery();
        askpermisssiondisplayontop();
        datePicker=findViewById(R.id.datepicker);
//        timePicker = findViewById(R.id.timepicker);

//        findViewById(R.id.schedule).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String days=editText.getText().toString();
//                set.add(Calendar.DAY_OF_MONTH,Integer.parseInt(days));
//            }
//        });

        findViewById(R.id.set_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year=datePicker.getYear();
                int day_of_month=datePicker.getDayOfMonth();
                int month = datePicker.getMonth();

                Intent intent = new Intent(MainActivity.this,SetTime.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("day_of_month",day_of_month);
                startActivity(intent);

                //                Calendar calendar =Calendar.getInstance();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    calendar.set(
//                    set.get(Calendar.YEAR),
//                    set.get(Calendar.MONTH),
//                    set.get(Calendar.DAY_OF_MONTH),
//                    timePicker.getHour(),
//                    timePicker.getMinute(),
//                            0
//                    );
//                }
//                setalarm(calendar.getTimeInMillis());
            }
        });
    }

    private void askpermisssiondisplayontop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                int REQUEST_CODE = 101;
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                myIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                myIntent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(myIntent, REQUEST_CODE);
            }
        }
    }


//    private void askpermisssionbattery() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            String packageName = this.getPackageName();
//            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
//            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                intent.setData(Uri.parse("package:" + packageName));
//                this.startActivity(intent);
//            }
//        }
//    }

    private void setalarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent =new Intent(this,alarm.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,0,intent,0);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent);
    }
}
