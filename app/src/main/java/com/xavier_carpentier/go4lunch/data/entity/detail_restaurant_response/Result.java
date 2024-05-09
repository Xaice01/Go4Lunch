package com.xavier_carpentier.go4lunch.data.entity.detail_restaurant_response;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class Result {

    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents;
    @SerializedName("adr_address")
    @Expose
    private String adrAddress;
    @SerializedName("business_status")
    @Expose
    private String businessStatus;
    @SerializedName("current_opening_hours")
    @Expose
    private CurrentOpeningHours currentOpeningHours;
    @SerializedName("editorial_summary")
    @Expose
    private EditorialSummary editorialSummary;
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("formatted_phone_number")
    @Expose
    private String formattedPhoneNumber;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon_background_color")
    @Expose
    private String iconBackgroundColor;
    @SerializedName("icon_mask_base_uri")
    @Expose
    private String iconMaskBaseUri;
    @SerializedName("international_phone_number")
    @Expose
    private String internationalPhoneNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("plus_code")
    @Expose
    private PlusCode plusCode;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews;
    @SerializedName("types")
    @Expose
    private List<String> types;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("user_ratings_total")
    @Expose
    private Integer userRatingsTotal;
    @SerializedName("utc_offset")
    @Expose
    private Integer utcOffset;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("wheelchair_accessible_entrance")
    @Expose
    private Boolean wheelchairAccessibleEntrance;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getAdrAddress() {
        return adrAddress;
    }

    public void setAdrAddress(String adrAddress) {
        this.adrAddress = adrAddress;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public CurrentOpeningHours getCurrentOpeningHours() {
        return currentOpeningHours;
    }

    public void setCurrentOpeningHours(CurrentOpeningHours currentOpeningHours) {
        this.currentOpeningHours = currentOpeningHours;
    }

    public EditorialSummary getEditorialSummary() {
        return editorialSummary;
    }

    public void setEditorialSummary(EditorialSummary editorialSummary) {
        this.editorialSummary = editorialSummary;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconBackgroundColor() {
        return iconBackgroundColor;
    }

    public void setIconBackgroundColor(String iconBackgroundColor) {
        this.iconBackgroundColor = iconBackgroundColor;
    }

    public String getIconMaskBaseUri() {
        return iconMaskBaseUri;
    }

    public void setIconMaskBaseUri(String iconMaskBaseUri) {
        this.iconMaskBaseUri = iconMaskBaseUri;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getWheelchairAccessibleEntrance() {
        return wheelchairAccessibleEntrance;
    }

    public void setWheelchairAccessibleEntrance(Boolean wheelchairAccessibleEntrance) {
        this.wheelchairAccessibleEntrance = wheelchairAccessibleEntrance;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("addressComponents");
        sb.append('=');
        sb.append(((this.addressComponents == null)?"<null>":this.addressComponents));
        sb.append(',');
        sb.append("adrAddress");
        sb.append('=');
        sb.append(((this.adrAddress == null)?"<null>":this.adrAddress));
        sb.append(',');
        sb.append("businessStatus");
        sb.append('=');
        sb.append(((this.businessStatus == null)?"<null>":this.businessStatus));
        sb.append(',');
        sb.append("currentOpeningHours");
        sb.append('=');
        sb.append(((this.currentOpeningHours == null)?"<null>":this.currentOpeningHours));
        sb.append(',');
        sb.append("editorialSummary");
        sb.append('=');
        sb.append(((this.editorialSummary == null)?"<null>":this.editorialSummary));
        sb.append(',');
        sb.append("formattedAddress");
        sb.append('=');
        sb.append(((this.formattedAddress == null)?"<null>":this.formattedAddress));
        sb.append(',');
        sb.append("formattedPhoneNumber");
        sb.append('=');
        sb.append(((this.formattedPhoneNumber == null)?"<null>":this.formattedPhoneNumber));
        sb.append(',');
        sb.append("geometry");
        sb.append('=');
        sb.append(((this.geometry == null)?"<null>":this.geometry));
        sb.append(',');
        sb.append("icon");
        sb.append('=');
        sb.append(((this.icon == null)?"<null>":this.icon));
        sb.append(',');
        sb.append("iconBackgroundColor");
        sb.append('=');
        sb.append(((this.iconBackgroundColor == null)?"<null>":this.iconBackgroundColor));
        sb.append(',');
        sb.append("iconMaskBaseUri");
        sb.append('=');
        sb.append(((this.iconMaskBaseUri == null)?"<null>":this.iconMaskBaseUri));
        sb.append(',');
        sb.append("internationalPhoneNumber");
        sb.append('=');
        sb.append(((this.internationalPhoneNumber == null)?"<null>":this.internationalPhoneNumber));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("openingHours");
        sb.append('=');
        sb.append(((this.openingHours == null)?"<null>":this.openingHours));
        sb.append(',');
        sb.append("photos");
        sb.append('=');
        sb.append(((this.photos == null)?"<null>":this.photos));
        sb.append(',');
        sb.append("placeId");
        sb.append('=');
        sb.append(((this.placeId == null)?"<null>":this.placeId));
        sb.append(',');
        sb.append("plusCode");
        sb.append('=');
        sb.append(((this.plusCode == null)?"<null>":this.plusCode));
        sb.append(',');
        sb.append("rating");
        sb.append('=');
        sb.append(((this.rating == null)?"<null>":this.rating));
        sb.append(',');
        sb.append("reference");
        sb.append('=');
        sb.append(((this.reference == null)?"<null>":this.reference));
        sb.append(',');
        sb.append("reviews");
        sb.append('=');
        sb.append(((this.reviews == null)?"<null>":this.reviews));
        sb.append(',');
        sb.append("types");
        sb.append('=');
        sb.append(((this.types == null)?"<null>":this.types));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("userRatingsTotal");
        sb.append('=');
        sb.append(((this.userRatingsTotal == null)?"<null>":this.userRatingsTotal));
        sb.append(',');
        sb.append("utcOffset");
        sb.append('=');
        sb.append(((this.utcOffset == null)?"<null>":this.utcOffset));
        sb.append(',');
        sb.append("vicinity");
        sb.append('=');
        sb.append(((this.vicinity == null)?"<null>":this.vicinity));
        sb.append(',');
        sb.append("website");
        sb.append('=');
        sb.append(((this.website == null)?"<null>":this.website));
        sb.append(',');
        sb.append("wheelchairAccessibleEntrance");
        sb.append('=');
        sb.append(((this.wheelchairAccessibleEntrance == null)?"<null>":this.wheelchairAccessibleEntrance));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}