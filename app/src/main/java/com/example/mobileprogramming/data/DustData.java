package com.example.mobileprogramming.data;

public class DustData {
    public String dustValue;
    public String dustGrade;
    public String microDustValue;
    public String microDustGrade;

    public String dustGradeStr(String grade) {
        if (grade == null) return "";
        switch (grade) {
            case "1":
                return "좋음";
            case "2":
                return "보통";
            case "3":
                return "나쁨";
            default:
                return "매우나쁨";
        }
    }
}
