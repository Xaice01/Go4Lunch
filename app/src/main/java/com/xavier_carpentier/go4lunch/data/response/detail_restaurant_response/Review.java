package com.xavier_carpentier.go4lunch.data.response.detail_restaurant_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("author_url")
    @Expose
    private String authorUrl;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("profile_photo_url")
    @Expose
    private String profilePhotoUrl;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("relative_time_description")
    @Expose
    private String relativeTimeDescription;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("translated")
    @Expose
    private Boolean translated;

    @SuppressWarnings("unused")
    public String getAuthorName() {
        return authorName;
    }

    @SuppressWarnings("unused")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @SuppressWarnings("unused")
    public String getAuthorUrl() {
        return authorUrl;
    }

    @SuppressWarnings("unused")
    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    @SuppressWarnings("unused")
    public String getLanguage() {
        return language;
    }

    @SuppressWarnings("unused")
    public void setLanguage(String language) {
        this.language = language;
    }

    @SuppressWarnings("unused")
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @SuppressWarnings("unused")
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @SuppressWarnings("unused")
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    @SuppressWarnings("unused")
    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    @SuppressWarnings("unused")
    public Integer getRating() {
        return rating;
    }

    @SuppressWarnings("unused")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @SuppressWarnings("unused")
    public String getRelativeTimeDescription() {
        return relativeTimeDescription;
    }

    @SuppressWarnings("unused")
    public void setRelativeTimeDescription(String relativeTimeDescription) {
        this.relativeTimeDescription = relativeTimeDescription;
    }

    @SuppressWarnings("unused")
    public String getText() {
        return text;
    }

    @SuppressWarnings("unused")
    public void setText(String text) {
        this.text = text;
    }

    @SuppressWarnings("unused")
    public Integer getTime() {
        return time;
    }

    @SuppressWarnings("unused")
    public void setTime(Integer time) {
        this.time = time;
    }

    @SuppressWarnings("unused")
    public Boolean getTranslated() {
        return translated;
    }

    @SuppressWarnings("unused")
    public void setTranslated(Boolean translated) {
        this.translated = translated;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Review.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("authorName");
        sb.append('=');
        sb.append(((this.authorName == null)?"<null>":this.authorName));
        sb.append(',');
        sb.append("authorUrl");
        sb.append('=');
        sb.append(((this.authorUrl == null)?"<null>":this.authorUrl));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("originalLanguage");
        sb.append('=');
        sb.append(((this.originalLanguage == null)?"<null>":this.originalLanguage));
        sb.append(',');
        sb.append("profilePhotoUrl");
        sb.append('=');
        sb.append(((this.profilePhotoUrl == null)?"<null>":this.profilePhotoUrl));
        sb.append(',');
        sb.append("rating");
        sb.append('=');
        sb.append(((this.rating == null)?"<null>":this.rating));
        sb.append(',');
        sb.append("relativeTimeDescription");
        sb.append('=');
        sb.append(((this.relativeTimeDescription == null)?"<null>":this.relativeTimeDescription));
        sb.append(',');
        sb.append("text");
        sb.append('=');
        sb.append(((this.text == null)?"<null>":this.text));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
        sb.append("translated");
        sb.append('=');
        sb.append(((this.translated == null)?"<null>":this.translated));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}