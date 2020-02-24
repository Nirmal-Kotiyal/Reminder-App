package com.example.nirmalkotiyal.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class AlarmService extends JobIntentService {


    static void enqueueWork(Context context,Intent intent){
        enqueueWork(context,AlarmService.class,123,intent);

    }
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("onrecieve","servicehandeled");

//        LayoutInflater layoutInflater  =         (LayoutInflater)AlarmService.this.getSystemService(LAYOUT_INFLATER_SERVICE);
//        final View popupView = layoutInflater.inflate(R.layout.activity_fullscreen, null);
//
//        final PopupWindow popupWindowDi = new PopupWindow(popupView,ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            popupWindowDi.getEnterTransition();
//        }


        Intent dialogIntent = new Intent(this, FullscreenActivity.class);
        dialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(dialogIntent);

    }

    @Override
    public void onCreate() {
        Log.d("onrecieve","servicestarted");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("onrecieve","servicestopped");
        super.onDestroy();
    }
}
