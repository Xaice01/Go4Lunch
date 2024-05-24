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
    public Prediction() {
    }

    /**
     *
     * @param reference
     * @param types
     * @param structuredFormatting
     * @param terms
     * @param matchedSubstrings
     * @param placeId
     * @param description
     */
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }

    public void setMatchedSubstrings(List<MatchedSubstring> matchedSubstrings) {
        this.matchedSubstrings = matchedSubstrings;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

    public void setStructuredFormatting(StructuredFormatting structuredFormatting) {
        this.structuredFormatting = structuredFormatting;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<String> getTypes() {
        return types;
    }

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