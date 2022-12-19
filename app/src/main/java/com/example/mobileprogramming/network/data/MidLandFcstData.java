package com.example.mobileprogramming.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MidLandFcstData {

    @SerializedName("rnSt3Am")
    @Expose
    private Integer rnSt3Am;
    @SerializedName("rnSt3Pm")
    @Expose
    private Integer rnSt3Pm;
    @SerializedName("rnSt4Am")
    @Expose
    private Integer rnSt4Am;
    @SerializedName("rnSt4Pm")
    @Expose
    private Integer rnSt4Pm;
    @SerializedName("rnSt5Am")
    @Expose
    private Integer rnSt5Am;
    @SerializedName("rnSt5Pm")
    @Expose
    private Integer rnSt5Pm;
    @SerializedName("rnSt6Am")
    @Expose
    private Integer rnSt6Am;
    @SerializedName("rnSt6Pm")
    @Expose
    private Integer rnSt6Pm;
    @SerializedName("rnSt7Am")
    @Expose
    private Integer rnSt7Am;
    @SerializedName("rnSt7Pm")
    @Expose
    private Integer rnSt7Pm;
    @SerializedName("rnSt8")
    @Expose
    private Integer rnSt8;
    @SerializedName("rnSt9")
    @Expose
    private Integer rnSt9;
    @SerializedName("rnSt10")
    @Expose
    private Integer rnSt10;
    @SerializedName("wf3Am")
    @Expose
    private String wf3Am;
    @SerializedName("wf3Pm")
    @Expose
    private String wf3Pm;
    @SerializedName("wf4Am")
    @Expose
    private String wf4Am;
    @SerializedName("wf4Pm")
    @Expose
    private String wf4Pm;
    @SerializedName("wf5Am")
    @Expose
    private String wf5Am;
    @SerializedName("wf5Pm")
    @Expose
    private String wf5Pm;
    @SerializedName("wf6Am")
    @Expose
    private String wf6Am;
    @SerializedName("wf6Pm")
    @Expose
    private String wf6Pm;
    @SerializedName("wf7Am")
    @Expose
    private String wf7Am;
    @SerializedName("wf7Pm")
    @Expose
    private String wf7Pm;
    @SerializedName("wf8")
    @Expose
    private String wf8;
    @SerializedName("wf9")
    @Expose
    private String wf9;
    @SerializedName("wf10")
    @Expose
    private String wf10;

    String getRnAmPm(int day) {
        switch (day) {
            case 3:
                return rnSt3Am + "% " + wf3Am + " " + rnSt3Pm + "% " + wf3Pm;
            case 4:
                return rnSt4Am + "% " + wf4Am + " " + rnSt4Pm + "% " + wf4Pm;
            case 5:
                return rnSt5Am + "% " + wf5Am + " " + rnSt5Pm + "% " + wf5Pm;
            case 6:
                return rnSt6Am + "% " + wf6Am + " " + rnSt6Pm + "% " + wf6Pm;
            case 7:
                return rnSt7Am + "% " + wf7Am + " " + rnSt7Pm + "% " + wf7Pm;
            case 8:
                return rnSt8 + "% " + wf8;
            case 9:
                return rnSt9 + "% " + wf9;
            default:
                return rnSt10 + "% " + wf10;

        }
    }

    public String getRn(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DATE, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String date = dateFormat.format(calendar.getTime());

        return date + " " + getRnAmPm(day);
    }

    public Integer getRnSt3Am() {
        return rnSt3Am;
    }

    public void setRnSt3Am(Integer rnSt3Am) {
        this.rnSt3Am = rnSt3Am;
    }

    public Integer getRnSt3Pm() {
        return rnSt3Pm;
    }

    public void setRnSt3Pm(Integer rnSt3Pm) {
        this.rnSt3Pm = rnSt3Pm;
    }

    public Integer getRnSt4Am() {
        return rnSt4Am;
    }

    public void setRnSt4Am(Integer rnSt4Am) {
        this.rnSt4Am = rnSt4Am;
    }

    public Integer getRnSt4Pm() {
        return rnSt4Pm;
    }

    public void setRnSt4Pm(Integer rnSt4Pm) {
        this.rnSt4Pm = rnSt4Pm;
    }

    public Integer getRnSt5Am() {
        return rnSt5Am;
    }

    public void setRnSt5Am(Integer rnSt5Am) {
        this.rnSt5Am = rnSt5Am;
    }

    public Integer getRnSt5Pm() {
        return rnSt5Pm;
    }

    public void setRnSt5Pm(Integer rnSt5Pm) {
        this.rnSt5Pm = rnSt5Pm;
    }

    public Integer getRnSt6Am() {
        return rnSt6Am;
    }

    public void setRnSt6Am(Integer rnSt6Am) {
        this.rnSt6Am = rnSt6Am;
    }

    public Integer getRnSt6Pm() {
        return rnSt6Pm;
    }

    public void setRnSt6Pm(Integer rnSt6Pm) {
        this.rnSt6Pm = rnSt6Pm;
    }

    public Integer getRnSt7Am() {
        return rnSt7Am;
    }

    public void setRnSt7Am(Integer rnSt7Am) {
        this.rnSt7Am = rnSt7Am;
    }

    public Integer getRnSt7Pm() {
        return rnSt7Pm;
    }

    public void setRnSt7Pm(Integer rnSt7Pm) {
        this.rnSt7Pm = rnSt7Pm;
    }

    public Integer getRnSt8() {
        return rnSt8;
    }

    public void setRnSt8(Integer rnSt8) {
        this.rnSt8 = rnSt8;
    }

    public Integer getRnSt9() {
        return rnSt9;
    }

    public void setRnSt9(Integer rnSt9) {
        this.rnSt9 = rnSt9;
    }

    public Integer getRnSt10() {
        return rnSt10;
    }

    public void setRnSt10(Integer rnSt10) {
        this.rnSt10 = rnSt10;
    }

    public String getWf3Am() {
        return wf3Am;
    }

    public void setWf3Am(String wf3Am) {
        this.wf3Am = wf3Am;
    }

    public String getWf3Pm() {
        return wf3Pm;
    }

    public void setWf3Pm(String wf3Pm) {
        this.wf3Pm = wf3Pm;
    }

    public String getWf4Am() {
        return wf4Am;
    }

    public void setWf4Am(String wf4Am) {
        this.wf4Am = wf4Am;
    }

    public String getWf4Pm() {
        return wf4Pm;
    }

    public void setWf4Pm(String wf4Pm) {
        this.wf4Pm = wf4Pm;
    }

    public String getWf5Am() {
        return wf5Am;
    }

    public void setWf5Am(String wf5Am) {
        this.wf5Am = wf5Am;
    }

    public String getWf5Pm() {
        return wf5Pm;
    }

    public void setWf5Pm(String wf5Pm) {
        this.wf5Pm = wf5Pm;
    }

    public String getWf6Am() {
        return wf6Am;
    }

    public void setWf6Am(String wf6Am) {
        this.wf6Am = wf6Am;
    }

    public String getWf6Pm() {
        return wf6Pm;
    }

    public void setWf6Pm(String wf6Pm) {
        this.wf6Pm = wf6Pm;
    }

    public String getWf7Am() {
        return wf7Am;
    }

    public void setWf7Am(String wf7Am) {
        this.wf7Am = wf7Am;
    }

    public String getWf7Pm() {
        return wf7Pm;
    }

    public void setWf7Pm(String wf7Pm) {
        this.wf7Pm = wf7Pm;
    }

    public String getWf8() {
        return wf8;
    }

    public void setWf8(String wf8) {
        this.wf8 = wf8;
    }

    public String getWf9() {
        return wf9;
    }

    public void setWf9(String wf9) {
        this.wf9 = wf9;
    }

    public String getWf10() {
        return wf10;
    }

    public void setWf10(String wf10) {
        this.wf10 = wf10;
    }
}
