package com.example.projectmcc;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class FetchData  {
    public static Data data[]=new Data[7];



    public static Data[] getData(Context mcontext, String tempurl)
    {
        DecimalFormat df=new DecimalFormat("#");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                     String condition;
                     double currentTemp;
                     double minTemp;
                     double maxTemp;
                     double feelsLike;
                     double pressure;
                     double humidity;

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonDaily=jsonResponse.getJSONArray("daily");
                    //today
                    JSONObject jsonOcurrent=jsonResponse.getJSONObject("current");

                    condition=jsonOcurrent.getJSONArray("weather").getJSONObject(0).getString("main");
                    currentTemp=jsonOcurrent.getDouble("temp");
                    maxTemp=jsonDaily.getJSONObject(0).getJSONObject("temp").getDouble("max");
                    minTemp=jsonDaily.getJSONObject(0).getJSONObject("temp").getDouble("min");
                    feelsLike=jsonOcurrent.getDouble("feels_like");
                    pressure=jsonOcurrent.getDouble("pressure");
                    humidity=jsonOcurrent.getDouble("humidity");

                    data[0]=new Data(condition,currentTemp,minTemp,maxTemp,feelsLike,pressure,humidity);

                    //rest
                    for(int i=1;i<7;i++)
                    {
                        condition=jsonDaily.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main");
                        currentTemp=-275;
                        maxTemp=jsonDaily.getJSONObject(i).getJSONObject("temp").getDouble("max");
                        minTemp=jsonDaily.getJSONObject(i).getJSONObject("temp").getDouble("min");
                        feelsLike=-275;
                        pressure=jsonDaily.getJSONObject(i).getDouble("pressure");
                        humidity=jsonDaily.getJSONObject(i).getDouble("humidity");

                        data[i]=new Data(condition,currentTemp,minTemp,maxTemp,feelsLike,pressure,humidity);
                    }
                    for(int i=0;i<7;i++)
                    {
                        Log.e("TAG", "onSuccess: "+data[i].condition+" "+data[i].maxTemp );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mcontext,error.toString().trim(),Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(mcontext);
        requestQueue.add(stringRequest);


        return data.clone();
    }

    public static Data[] getter()
    {
        Data[] tmp=data;
        return data.clone();
    }

}
