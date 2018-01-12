package com.example.student.dd2018011201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i=0; i<array.length(); i++)
                            {
                                JSONObject obj1 = array.getJSONObject(i);
                                StringBuilder sb = new StringBuilder();
                                sb.append("district: " + obj1.getString("district"));
                                sb.append(", address: " + obj1.getString("address"));
                                sb.append(", tel: " + obj1.getString("tel"));
                                sb.append(", opening_hours: " + obj1.getString("opening_hours"));
                                Log.d("Object", sb.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(request);
        queue.start();
    }
}
