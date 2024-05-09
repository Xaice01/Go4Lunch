package com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class PlusCode {

    @SerializedName("compound_code")
    @Expose
    private String compoundCode;
    @SerializedName("global_code")
    @Expose
    private String globalCode;

    public String getCompoundCode() {
        return compoundCode;
    }

    public void setCompoundCode(String compoundCode) {
        this.compoundCode = compoundCode;
    }

    public String getGlobalCode() {
        return globalCode;
    }

    public void setGlobalCode(String globalCode) {
        this.globalCode = globalCode;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PlusCode.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("compoundCode");
        sb.append('=');
        sb.append(((this.compoundCode == null)?"<null>":this.compoundCode));
        sb.append(',');
        sb.append("globalCode");
        sb.append('=');
        sb.append(((this.globalCode == null)?"<null>":this.globalCode));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}