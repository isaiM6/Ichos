package com.daclink.drew.sp22.cst438_project01_starter.api_implementation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Used to store the result of a query of the news API
 * @link newsAPI.org
 * @see com.daclink.drew.sp22.cst438_project01_starter.api_implementation.apis.NewsSearchService
 */
public class NewsResult {
    @SerializedName("source")
    @Expose
    private NewsResultSource source;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("urlToImage")
    @Expose
    private String imageUrl;

    @SerializedName("publishedAt")
    @Expose
    private String publicationDate;

    @SerializedName("content")
    @Expose
    private String content;

    public NewsResultSource getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getContent() {
        return content;
    }
}
