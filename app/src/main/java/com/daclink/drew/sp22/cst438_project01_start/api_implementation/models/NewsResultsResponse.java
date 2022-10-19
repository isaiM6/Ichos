package com.daclink.drew.sp22.cst438_project01_start.api_implementation.models;

import com.daclink.drew.sp22.cst438_project01_start.api_implementation.apis.NewsSearchService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Used to store all the results in a response from a query of the news API
 * @link newsAPI.org
 * @see NewsSearchService
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
