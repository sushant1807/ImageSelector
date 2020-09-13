package com.sushant.android.imageselector.models;

public class ImageModel {
    private String tags;
    private String user;
    private String previewURL;
    private int likes;
    private long views;
    private String webformatURL;
    private int favorites;

    public ImageModel(String tags, String previewURL, String user, int likes, long views,
                      String webformatURL, int favorites) {
        this.tags = tags;
        this.previewURL = previewURL;
        this.user = user;
        this.likes = likes;
        this.views = views;
        this.webformatURL = webformatURL;
        this.favorites = favorites;
    }

    public String getTags() {
        return tags.toUpperCase();
    }

    public String getUser() {
        return "By: " + user;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getLikes() {
        return "Likes: " + likes;
    }

    public String getViews() {
        return "Views: " + views;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public String getFavorites() {
        return String.valueOf(favorites);
    }
}