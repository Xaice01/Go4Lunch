package com.xavier_carpentier.go4lunch.data.response.autocomplete_response;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction implements Serializable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("matched_substrings")
    @Expose
    private List<MatchedSubstring> matchedSubstrings;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("structured_formatting")
    @Expose
    private StructuredFormatting structuredFormatting;
    @SerializedName("terms")
    @Expose
    private List<Term> terms;
    @SerializedName("types")
    @Expose
    private List<String> types;
    private final static long serialVersionUID = -5936930869472085638L;

    /**
     * No args constructor for use in serialization
     *
     */
    @SuppressWarnings("unused")
    public Prediction() {
    }

    @SuppressWarnings("unused")
    public Prediction(String description, List<MatchedSubstring> matchedSubstrings, String placeId, String reference, StructuredFormatting structuredFormatting, List<Term> terms, List<String> types) {
        super();
        this.description = description;
        this.matchedSubstrings = matchedSubstrings;
        this.placeId = placeId;
        this.reference = reference;
        this.structuredFormatting = structuredFormatting;
        this.terms = terms;
        this.types = types;
    }

    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }

    @SuppressWarnings("unused")
    public void setMatchedSubstrings(List<MatchedSubstring> matchedSubstrings) {
        this.matchedSubstrings = matchedSubstrings;
    }

    public String getPlaceId() {
        return placeId;
    }

    @SuppressWarnings("unused")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @SuppressWarnings("unused")
    public String getReference() {
        return reference;
    }

    @SuppressWarnings("unused")
    public void setReference(String reference) {
        this.reference = reference;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

    @SuppressWarnings("unused")
    public void setStructuredFormatting(StructuredFormatting structuredFormatting) {
        this.structuredFormatting = structuredFormatting;
    }
    @SuppressWarnings("unused")
    public List<Term> getTerms() {
        return terms;
    }

    @SuppressWarnings("unused")
    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    @SuppressWarnings("unused")
    public List<String> getTypes() {
        return types;
    }

    @SuppressWarnings("unused")
    public void setTypes(List<String> types) {
        this.types = types;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Prediction.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("matchedSubstrings");
        sb.append('=');
        sb.append(((this.matchedSubstrings == null)?"<null>":this.matchedSubstrings));
        sb.append(',');
        sb.append("placeId");
        sb.append('=');
        sb.append(((this.placeId == null)?"<null>":this.placeId));
        sb.append(',');
        sb.append("reference");
        sb.append('=');
        sb.append(((this.reference == null)?"<null>":this.reference));
        sb.append(',');
        sb.append("structuredFormatting");
        sb.append('=');
        sb.append(((this.structuredFormatting == null)?"<null>":this.structuredFormatting));
        sb.append(',');
        sb.append("terms");
        sb.append('=');
        sb.append(((this.terms == null)?"<null>":this.terms));
        sb.append(',');
        sb.append("types");
        sb.append('=');
        sb.append(((this.types == null)?"<null>":this.types));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}