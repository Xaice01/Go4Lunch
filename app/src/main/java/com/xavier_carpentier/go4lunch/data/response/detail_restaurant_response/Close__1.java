package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Close__1 {

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("time")
    @Expose
    private String time;

    @SuppressWarnings("unused")
    public Integer getDay() {
        return day;
    }

    @SuppressWarnings("unused")
    public void setDay(Integer day) {
        this.day = day;
    }

    @SuppressWarnings("unused")
    public String getTime() {
        return time;
    }

    @SuppressWarnings("unused")
    public void setTime(String time) {
        this.time = time;
    }


}