package com.example.projectmcc;

public class Data {
    public String condition;
    public double currentTemp;
    public double minTemp;
    public double maxTemp;
    public double feelsLike;
    public double pressure;
    public double humidity;

    Data(String condition,double currentTemp,double minTemp,double maxTemp ,double feelsLike,double pressure,double humidity)
    {
        this.condition=condition;
        this.currentTemp=currentTemp;
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
        this.feelsLike=feelsLike;
        this.pressure=pressure;
        this.humidity=humidity;
    }
}
