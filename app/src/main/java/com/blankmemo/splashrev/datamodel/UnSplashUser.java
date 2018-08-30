package com.blankmemo.splashrev.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnSplashUser implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("twitter_username")
    @Expose
    private Object twitterUsername;
    @SerializedName("portfolio_url")
    @Expose
    private String portfolioUrl;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("instagram_username")
    @Expose
    private String instagramUsername;
    @SerializedName("total_collections")
    @Expose
    private Integer totalCollections;
    @SerializedName("total_likes")
    @Expose
    private Integer totalLikes;
    @SerializedName("total_photos")
    @Expose
    private Integer totalPhotos;


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Object getTwitterUsername() {
        return twitterUsername;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public Integer getTotalCollections() {
        return totalCollections;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }
}
