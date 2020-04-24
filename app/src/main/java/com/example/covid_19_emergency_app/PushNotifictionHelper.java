package com.example.covid_19_emergency_app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PushNotifictionHelper extends AsyncTask<String, Void, String>{
    private static final String TAG = "PushNotifictionHelper";
    private final static String AUTH_KEY_FCM = "AAAAlitZHa4:APA91bG96LGXYw_r2lMTtVj1PIn-BO1ZbJOiWZtFudkVY0jIgJfuSIUcrivso7eD5VhFMKPXAS47y0gJLoua8ykOtBPzes_SCF3uzQS9pSVvYMQWacf7T05JP8iCuGZMux6yd5khA_Ff";
    private final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    @Override
    protected String doInBackground(String... strings) {
        String deviceToken = strings[0];
        String result = "";
        try {
            URL url = new URL(API_URL_FCM);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();

            json.put("to", deviceToken.trim());
            JSONObject info = new JSONObject();
            info.put("title", strings[1]); // Notification title
            info.put("body", "Someone needs your help near your location"); // Notification
            // body
            json.put("notification", info);

            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            Log.d(TAG, "Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                Log.d(TAG, output);
            }
            result = "SUCCESS";
        } catch (Exception e) {
            Log.d(TAG, String.valueOf(e));
            result = "FAILURE";
        }

        return result;
    }

}


