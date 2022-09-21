package com.daclink.drew.sp22.cst438_project01_starter.api_implementation.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.models.NewsResultsResponse;

/**
 * Interface storing the get requests used to access the news API
 * @link newsAPI.org
 */
public interface NewsSearchService {
    /**
     * Get request used to search the entire API
     * @param query the keyword to search for
     * @param sources the news sources to search in
     * @param sortBy the way the results should be sorted. Accepts 'publishedAt',
     *               'popularity', and 'relevancy' as valid arguments.
     * @param apiKey the api key used to access the api
     */
    @GET("/v2/everything")
    Call<NewsResultsResponse> searchNews(
            @Query("q") String query,
            @Query("sources") String sources,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );

    /**
     * Get request used to search the top headlines in the API
     * @param query the keyword to search for
     * @param sources the news sources to search in
     * @param sortBy the way the results should be sorted. Accepts 'publishedAt',
     *               'popularity', and 'relevancy' as valid arguments.
     * @param apiKey the api key used to access the api
     */
    @GET("/v2/top-headlines")
    Call<NewsResultsResponse> searchTopHeadlines(
            @Query("q") String query,
            @Query("sources") String sources,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}
