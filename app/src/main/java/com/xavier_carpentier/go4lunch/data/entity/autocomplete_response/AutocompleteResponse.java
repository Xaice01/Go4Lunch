package com.xavier_carpentier.go4lunch.data.entity.autocomplete_response;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutocompleteResponse implements Serializable
{

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public AutocompleteResponse() {
    }

    /**
     *
     * @param predictions
     * @param status
     */
    public AutocompleteResponse(List<Prediction> predictions, String status) {
        super();
        this.predictions = predictions;
        this.status = status;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
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
        sb.append(AutocompleteResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("predictions");
        sb.append('=');
        sb.append(((this.predictions == null)?"<null>":this.predictions));
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