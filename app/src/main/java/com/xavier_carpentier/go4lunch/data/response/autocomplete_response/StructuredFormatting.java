package com.xavier_carpentier.go4lunch.data.response.autocomplete_response;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructuredFormatting implements Serializable
{

    @SerializedName("main_text")
    @Expose
    private String mainText;
    @SerializedName("main_text_matched_substrings")
    @Expose
    private List<MainTextMatchedSubstring> mainTextMatchedSubstrings;
    @SerializedName("secondary_text")
    @Expose
    private String secondaryText;
    private final static long serialVersionUID = -1495520132544387662L;

    /**
     * No args constructor for use in serialization
     *
     */
    public StructuredFormatting() {
    }

    /**
     *
     * @param mainText
     * @param mainTextMatchedSubstrings
     * @param secondaryText
     */
    public StructuredFormatting(String mainText, List<MainTextMatchedSubstring> mainTextMatchedSubstrings, String secondaryText) {
        super();
        this.mainText = mainText;
        this.mainTextMatchedSubstrings = mainTextMatchedSubstrings;
        this.secondaryText = secondaryText;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public List<MainTextMatchedSubstring> getMainTextMatchedSubstrings() {
        return mainTextMatchedSubstrings;
    }

    public void setMainTextMatchedSubstrings(List<MainTextMatchedSubstring> mainTextMatchedSubstrings) {
        this.mainTextMatchedSubstrings = mainTextMatchedSubstrings;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StructuredFormatting.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("mainText");
        sb.append('=');
        sb.append(((this.mainText == null)?"<null>":this.mainText));
        sb.append(',');
        sb.append("mainTextMatchedSubstrings");
        sb.append('=');
        sb.append(((this.mainTextMatchedSubstrings == null)?"<null>":this.mainTextMatchedSubstrings));
        sb.append(',');
        sb.append("secondaryText");
        sb.append('=');
        sb.append(((this.secondaryText == null)?"<null>":this.secondaryText));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}