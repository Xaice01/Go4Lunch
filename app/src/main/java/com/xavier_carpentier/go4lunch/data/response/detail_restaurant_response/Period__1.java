package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class Period__1 {

    @SerializedName("close")
    @Expose
    private Close__1 close;
    @SerializedName("open")
    @Expose
    private Open__1 open;

    @SuppressWarnings("unused")
    public Close__1 getClose() {
        return close;
    }

    @SuppressWarnings("unused")
    public void setClose(Close__1 close) {
        this.close = close;
    }

    @SuppressWarnings("unused")
    public Open__1 getOpen() {
        return open;
    }

    @SuppressWarnings("unused")
    public void setOpen(Open__1 open) {
        this.open = open;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Period__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
        sb.append(',');
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}