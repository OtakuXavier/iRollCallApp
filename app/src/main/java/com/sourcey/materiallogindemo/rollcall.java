package com.sourcey.materiallogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.text.*;
import android.widget.TextView;


import org.apache.http.*;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class rollcall extends AppCompatActivity {

    String captcha = "";
    String jResult = "";
    private TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rollcall);
        textView = (TextView)findViewById(R.id.TextView01);
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[]{"https://majaja.herokuapp.com/roll_calling/rollcall?student_id=test001"});
        try {
            JSONObject reader = new JSONObject();
            captcha = reader.getString("stat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textView.setText(captcha);
    }




    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";

            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;



        }

    }


    private class RollCallAuthTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";

            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;



        }



        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject reader = new JSONObject(result);
                jResult = reader.getString("stat");
                textView.setText(jResult);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public void readWebpage(View view) {
        RollCallAuthTask task = new RollCallAuthTask();
            task.execute(new String[]{"https://majaja.herokuapp.com/roll_calling/isrollcall/?student_id=test001&captcha="+captcha});
        Log.d("authload", captcha);
    }
}
