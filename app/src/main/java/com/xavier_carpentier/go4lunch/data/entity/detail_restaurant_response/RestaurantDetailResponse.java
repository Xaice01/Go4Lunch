package com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class RestaurantDetailResponse {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private String status;

    public RestaurantDetailResponse(List<Object> htmlAttributions, Result result, String status) {
        this.htmlAttributions = htmlAttributions;
        this.result = result;
        this.status = status;
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RestaurantDetailResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("htmlAttributions");
        sb.append('=');
        sb.append(((this.htmlAttributions == null)?"<null>":this.htmlAttributions));
        sb.append(',');
        sb.append("result");
        sb.append('=');
        sb.append(((this.result == null)?"<null>":this.result));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
