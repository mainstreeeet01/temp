package com.example.mobileprogramming.data;

public class UltraShortForecastData {
    public String rainPer;
    public String rainType;
    public String curTemp;
    public String maxTemp;
    public String minTemp;
    public String skyType;

    public String getSkyTypeName() {
        if (skyType == null) return "맑음";
        switch (skyType) {
            case "3":
                return "구름많음";
            case "4":
                return "흐림";
            default:
                return "맑음";
        }
    }
}
