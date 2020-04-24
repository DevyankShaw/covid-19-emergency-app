package com.example.covid_19_emergency_app;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    public static String token;
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()) {
                    Log.i(TAG, "Task Failed");
                    return;
                }
                token = task.getResult().getToken();
                Log.i(TAG, "The result: "+task.getResult().getToken());
            }
        });
    }
}
