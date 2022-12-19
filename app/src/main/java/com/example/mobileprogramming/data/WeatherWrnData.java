package com.example.mobileprogramming.data;

public class WeatherWrnData {
    public String t1;
    public String t2;
    public String t3;
    public String t4;
    public String t5; // 시간

    public String getContent() {
        return t1 + "\n" +
                t2 + "\n" +
                t3 + "\n" +
                t4 + "\n\n" +
                t5 + "\n";
    }
}
