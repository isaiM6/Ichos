package com.daclink.drew.sp22.cst438_project01_starter.api_implementation.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.models.NewsResultsResponse;
import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.repository.NewsRepository;

/**
 * The view model used to access the news API through the repository. Used by the View to
 * observe for changes in the search results.
 * @see androidx.lifecycle.AndroidViewModel
 */
public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;
    private LiveData<NewsResultsResponse> newsResponseLiveData;

    public NewsViewModel(@NonNull Application application) {
        super(application);
    }

    /** Initializes variables */
    public void init() {
        newsRepository = new NewsRepository();
        newsResponseLiveData = newsRepository.getVolumesResponseLiveData();
    }

    /**
     * Used to search the news API using the news repository
     * @param keyword the keyword to search for
     * @param source the news source to search in
     * @param sortBy the way the results should be sorted. Accepts 'publishedAt',
     *               'popularity', and 'relevancy' as valid arguments.
     */
    public void searchNews(String keyword, String source, String sortBy) {
        newsRepository.searchNews(keyword, source, sortBy, "22668da3374a4f8885c094cec719ae51");
    }

    /**
     * Used to search the top headlines in the news API using the news repository
     * @param keyword the keyword to search for
     * @param source the news source to search in
     * @param sortBy the way the results should be sorted. Accepts 'publishedAt',
     *               'popularity', and 'relevancy' as valid arguments.
     */
    public void searchTopHeadlines(String keyword, String source, String sortBy) {
        newsRepository.searchTopHeadlines(keyword, source, sortBy, "22668da3374a4f8885c094cec719ae51");
    }

    /**
     * Used to access the live data results
     * @return The live data of the last search results
     */
    public LiveData<NewsResultsResponse> getVolumesResponseLiveData() {
        return newsResponseLiveData;
    }
}
