package com.daclink.drew.sp22.cst438_project01_starter.api_implementation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Used to store the source of a query of the news API
 * @link newsAPI.org
 * @see com.daclink.drew.sp22.cst438_project01_starter.api_implementation.apis.NewsSearchService
 */
public class NewsResultSource {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
