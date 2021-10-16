package com.example.projectmcc;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    public String condition;
    public String currentTemp;
    public String minTemp;
    public String maxTemp;
    public String feelsLike;
    public String pressure;
    public String humidity;
    public String date;

    Data(String condition,String currentTemp,String minTemp,String maxTemp ,String feelsLike,String pressure,String humidity,String date)
    {
        this.condition=condition;
        this.currentTemp=currentTemp;
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
        this.feelsLike=feelsLike;
        this.pressure=pressure;
        this.humidity=humidity;
        this.date=date;
    }

    protected Data(Parcel in) {
        condition = in.readString();
        currentTemp = in.readString();
        minTemp = in.readString();
        maxTemp = in.readString();
        feelsLike = in.readString();
        pressure = in.readString();
        humidity = in.readString();
        date = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getCondition() {
        return condition;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }
    public String getDate()
    {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(condition);
        dest.writeString(currentTemp);
        dest.writeString(minTemp);
        dest.writeString(maxTemp);
        dest.writeString(feelsLike);
        dest.writeString(pressure);
        dest.writeString(humidity);
        dest.writeString(date);
    }
}
