package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class EditorialSummary {

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("overview")
    @Expose
    private String overview;

    @SuppressWarnings("unused")
    public String getLanguage() {
        return language;
    }

    @SuppressWarnings("unused")
    public void setLanguage(String language) {
        this.language = language;
    }

    @SuppressWarnings("unused")
    public String getOverview() {
        return overview;
    }

    @SuppressWarnings("unused")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EditorialSummary.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("overview");
        sb.append('=');
        sb.append(((this.overview == null)?"<null>":this.overview));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}