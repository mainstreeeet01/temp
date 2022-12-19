package com.example.mobileprogramming.data;

public class UVViewData {
    public String today;
    public String tomorrow;
    public String firstName;
    public String secondName;
    public String thirdName;

    public String getUVGrade(String value) {
        if (value == null) return "낮음";
        switch (value) {
            case "0":
            case "1":
            case "2":
                return "낮음";
            case "3":
            case "4":
            case "5":
                return "보통";
            case "6":
            case "7":
                return "높음";
            case "8":
            case "9":
            case "10":
                return "매우높음";
            default:
                return "위험";
        }
    }

    public String getName() {
        if (thirdName != null && !thirdName.isEmpty()) {
            return thirdName;
        } else if (secondName != null && !secondName.isEmpty()) {
            return secondName;
        } else
            return firstName;
    }
}
