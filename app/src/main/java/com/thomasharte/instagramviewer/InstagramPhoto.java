package com.thomasharte.instagramviewer;

/**
 * Created by thomasharte on 11/09/2014.
 */
public class InstagramPhoto {

    private String caption;
    private String username;
    private String imageUrl;
    private int imageHeight;
    private int likesCount;
    private String profilePictureUrl;

    public InstagramPhoto(String caption, String username, String profilePictureUrl, String imageUrl, int imageHeight, int likesCount) {
        this.caption = caption;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likesCount = likesCount;
    }

    public String getCaption() {
        return caption;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
