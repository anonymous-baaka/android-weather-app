package com.example.projectmcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import com.google.android.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public int isSet=0;
    FusedLocationProviderClient client;
    LatLng latLng;
    DecimalFormat df=new DecimalFormat("#.##");
    String URL="";

    static TextView tv_condition,tv_max,tv_min;
    static ImageView iv_condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        client = LocationServices.getFusedLocationProviderClient(this);

        tv_condition=(TextView) findViewById(R.id.tv_condition);
        tv_max=(TextView)findViewById(R.id.tv_temphigh);
        tv_min=(TextView)findViewById(R.id.tv_templow);
        iv_condition=(ImageView)findViewById(R.id.iv_condition);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getMyLocation();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
    }

    public void getMyLocation()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>()
        {
            @Override
            public void onSuccess(final Location location)
            {
                latLng=new LatLng(Double.parseDouble(df.format(location.getLatitude())),Double.parseDouble(df.format(location.getLongitude())));
                //Log.e("TAG", "onSuccess: "+latLng.latitude+" "+latLng.longitude);

                BuildURL buildURL = new BuildURL();
                URL=buildURL.build(latLng);

                ExecutorService executor= Executors.newSingleThreadExecutor();
                Handler handler=new Handler(Looper.getMainLooper());

                final Data[][] data = {new Data[8]};

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        FetchData fetchData=new FetchData();
                        FetchData.getData(getApplicationContext(),URL);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                data[0] =FetchData.getter();
                                /*for(int i=0;i<7;i++)
                                {
                                    Log.e("TAG", "onSuccess: "+ data[0][i].condition+" "+ data[0][i].maxTemp );
                                }*/
                            }
                        });
                    }
                });



                /*try {
                    parseJSON(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
            });
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        //JSONObject jsonCurrent= jsonObject.getJSONObject("current");

    }

    public static void setter(String str_cond,String str_max,String str_min)
    {
        tv_condition.setText(str_cond);
        tv_max.setText(str_max);
        tv_min.setText(str_min);

        if(str_cond=="Rain")
        iv_condition.setImageResource(R.drawable.ic_rain);
        else if(str_cond=="Clear")
            iv_condition.setImageResource(R.drawable.ic_clear);
        else if(str_cond=="Cloudy")
            iv_condition.setImageResource(R.drawable.ic_cloudy);
        else if(str_cond=="Storm")
            iv_condition.setImageResource(R.drawable.ic_storm);
        else if(str_cond=="Snow")
            iv_condition.setImageResource(R.drawable.ic_snow);
    }
}