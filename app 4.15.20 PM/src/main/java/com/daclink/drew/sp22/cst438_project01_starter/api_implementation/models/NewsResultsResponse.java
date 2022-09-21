package com.daclink.drew.sp22.cst438_project01_starter.api_implementation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Used to store all the results in a response from a query of the news API
 * @link newsAPI.org
 * @see com.daclink.drew.sp22.cst438_project01_starter.api_implementation.apis.NewsSearchService
 */
public class NewsResultsResponse {
    @SerializedName("totalResults")
    @Expose
    int totalResults;

    @SerializedName("articles")
    @Expose
    List<NewsResult> results = null;

    public List<NewsResult> getResults() {
        return results;
    }

    public int getTotalItems() {
        return totalResults;
    }
}
