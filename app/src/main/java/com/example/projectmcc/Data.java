package com.example.projectmcc;

public class Data {
    public String condition;
    public String currentTemp;
    public String minTemp;
    public String maxTemp;
    public String feelsLike;
    public String pressure;
    public String humidity;

    Data(String condition,String currentTemp,String minTemp,String maxTemp ,String feelsLike,String pressure,String humidity)
    {
        this.condition=condition;
        this.currentTemp=currentTemp;
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
        this.feelsLike=feelsLike;
        this.pressure=pressure;
        this.humidity=humidity;
    }

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
}
