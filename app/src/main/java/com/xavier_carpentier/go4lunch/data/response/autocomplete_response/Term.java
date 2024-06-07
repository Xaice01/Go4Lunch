package com.xavier_carpentier.go4lunch.data.response.autocomplete_response;

import androidx.annotation.NonNull;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Term implements Serializable
{

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("value")
    @Expose
    private String value;
    private final static long serialVersionUID = 311862859502067973L;

    /**
     * No args constructor for use in serialization
     *
     */
    @SuppressWarnings("unused")
    public Term() {
    }

    @SuppressWarnings("unused")
    public Term(Integer offset, String value) {
        super();
        this.offset = offset;
        this.value = value;
    }

    @SuppressWarnings("unused")
    public Integer getOffset() {
        return offset;
    }

    @SuppressWarnings("unused")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @SuppressWarnings("unused")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Term.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("offset");
        sb.append('=');
        sb.append(((this.offset == null)?"<null>":this.offset));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}