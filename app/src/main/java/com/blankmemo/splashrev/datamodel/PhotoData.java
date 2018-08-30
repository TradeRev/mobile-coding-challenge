package com.blankmemo.splashrev.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hongyuchen on 2018-08-25.
 */

public class PhotoData implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("urls")
    @Expose
    private PhotoUrls urls;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("user")
    @Expose
    private UnSplashUser user;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public PhotoUrls getUrls() {
        return urls;
    }

    public Integer getLikes() {
        return likes;
    }

    public UnSplashUser getUser() {
        return user;
    }


}
