package com.example.projectmcc;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class DetailedView extends AppCompatActivity {
    static TextView tv_condition,tv_max,tv_min,tv_humidity,tv_pressure,tv_day;
    static ImageView iv_condition;
    ImageView iv_condition2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Data data=getIntent().getParcelableExtra("data");

        tv_condition=  findViewById(R.id.tv_dcondition);
        tv_max=(TextView)findViewById(R.id.tv_dtemphigh);
        tv_min=(TextView)findViewById(R.id.tv_dtemplow);
        iv_condition=(ImageView)findViewById(R.id.iv_dcondition);
        tv_pressure=(TextView)findViewById(R.id.tv_dpressure);
        tv_humidity=(TextView)findViewById(R.id.tv_dhumidity);
        tv_day=(TextView)findViewById(R.id.tv_dtoday);

        tv_condition.setText(data.getCondition());
        tv_max.setText(data.getMaxTemp());
        tv_min.setText(data.getMinTemp());
        tv_pressure.setText(data.getPressure()+" hPa");
        tv_humidity.setText(data.getHumidity()+" %");
        tv_day.setText(parseDate(data.getDate()));

        String str_cond=data.getCondition();
        if(str_cond.equals("Rain"))
            this.iv_condition.setImageResource(R.drawable.ic_rain);
        else if(str_cond.equals("Clear"))
            this.iv_condition.setImageResource(R.drawable.ic_clear);
        else if(str_cond.equals("Clouds"))
            this.iv_condition.setImageResource(R.drawable.ic_cloudy);
        else if(str_cond.equals("Storm"))
            this.iv_condition.setImageResource(R.drawable.ic_storm);
        else if(str_cond.equals("Snow")) {
            this.iv_condition.setImageResource(R.drawable.ic_snow);
        } else if(str_cond.equals("Thunderstorm"))
            this.iv_condition.setImageResource(R.drawable.ic_storm);
        else if(str_cond.equals("Haze"))
            this.iv_condition.setImageResource(R.drawable.ic_fog);
        else if(str_cond.equals("Smoke"))
            this.iv_condition.setImageResource(R.drawable.ic_fog);

    }

    String parseDate(String ip) {
        ip += "000";
        Date date = new Date(Long.parseLong(ip));
        return date.toString();
    }
}
