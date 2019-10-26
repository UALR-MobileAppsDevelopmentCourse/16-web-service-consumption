package edu.ualr.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by irconde on 2019-10-25.
 */
public class Step {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;

    public String getDescription() {
        return description;
    }

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

}
