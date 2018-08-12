package com.arctouch.codechallenge.detail.contract

import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.model.Movie

class DetailContract {

    interface DetailPresenterContract {
        fun attachActivityView(activityView: DetailContract.DetailActivityViewContract)
        fun loadMovieData(movieId: Long)
    }

    interface DetailActivityViewContract {
        fun getTmdbApi() : TmdbApi
        fun showMovieData(movie: Movie)
    }

}