package com.arctouch.codechallenge.home.contract

import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.model.Movie

class HomeContract {

    interface HomePresenterContract {
        fun attachActivityView(activityView: HomeActivityViewContract)
        fun cacheGenres()
        fun listUpcomingMovies()
    }

    interface HomeActivityViewContract {
        fun getTmdbApi() : TmdbApi
        fun showMoviesList(movieList: List<Movie>)
    }

}