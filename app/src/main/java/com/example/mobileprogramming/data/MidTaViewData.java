package com.example.mobileprogramming.data;

public class MidTaViewData {
    public int taMin3;
    public int taMax3;
    public int taMin4;
    public int taMax4;
    public int taMin5;
    public int taMax5;
    public int taMin6;
    public int taMax6;
    public int taMin7;
    public int taMax7;
    public int taMin8;
    public int taMax8;
    public int taMin9;
    public int taMax9;
    public int taMin10;
    public int taMax10;

    public String getContent() {
        return taMin3 + "/" + taMin3 + "\n"
                + taMin4 + "/" + taMin4 + "\n"
                + taMin5 + "/" + taMin5 + "\n"
                + taMin6 + "/" + taMin6 + "\n"
                + taMin7 + "/" + taMin7 + "\n"
                + taMin8 + "/" + taMin8 + "\n"
                + taMin9 + "/" + taMin9 + "\n"
                + taMin10 + "/" + taMin10 + "\n";
    }

    public String getRnAmPm(int day) {
        switch (day) {
            case 3:
                return taMin3 + "/" + taMin3;
            case 4:
                return taMin4 + "/" + taMin4;
            case 5:
                return taMin5 + "/" + taMin5;
            case 6:
                return taMin6 + "/" + taMin6;
            case 7:
                return taMin7 + "/" + taMin7;
            case 8:
                return taMin8 + "/" + taMin8;
            case 9:
                return taMin9 + "/" + taMin9;
            default:
                return taMin10 + "/" + taMin10;

        }
    }
}
