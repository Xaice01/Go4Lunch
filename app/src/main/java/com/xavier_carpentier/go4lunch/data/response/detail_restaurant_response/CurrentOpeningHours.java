package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import androidx.annotation.NonNull;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class CurrentOpeningHours {

    @SerializedName("open_now")
    @Expose
    private Boolean openNow;
    @SerializedName("periods")
    @Expose
    private List<Period> periods;
    @SerializedName("weekday_text")
    @Expose
    private List<String> weekdayText;

    @SuppressWarnings("unused")
    public Boolean getOpenNow() {
        return openNow;
    }

    @SuppressWarnings("unused")
    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    @SuppressWarnings("unused")
    public List<Period> getPeriods() {
        return periods;
    }

    @SuppressWarnings("unused")
    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @SuppressWarnings("unused")
    public List<String> getWeekdayText() {
        return weekdayText;
    }

    @SuppressWarnings("unused")
    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CurrentOpeningHours.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("openNow");
        sb.append('=');
        sb.append(((this.openNow == null)?"<null>":this.openNow));
        sb.append(',');
        sb.append("periods");
        sb.append('=');
        sb.append(((this.periods == null)?"<null>":this.periods));
        sb.append(',');
        sb.append("weekdayText");
        sb.append('=');
        sb.append(((this.weekdayText == null)?"<null>":this.weekdayText));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}