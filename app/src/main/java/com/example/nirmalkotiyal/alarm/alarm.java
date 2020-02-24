package com.example.nirmalkotiyal.alarm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.support.v4.content.ContextCompat.startForegroundService;

public class alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("onrecieve","adfsdf");

        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Intent i = new Intent(context, AlarmService.class);
            AlarmService.enqueueWork(context,i);
        }
        else{
            Intent i = new Intent(context, AlarmService.class);
            AlarmService.enqueueWork(context,i);
        }
        //        Intent i = new Intent();
//        i.setClassName("com.example.nirmalkotiyal.alarm", "com.example.nirmalkotiyal.alarm.FullscreenActivity");
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(i);
    }

}
