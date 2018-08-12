package com.arctouch.codechallenge.home.presenter

import com.arctouch.codechallenge.home.contract.HomeContract
import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.data.Cache
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter : HomeContract.HomePresenterContract {

    private lateinit var mActivityView: HomeContract.HomeActivityViewContract

    override fun attachActivityView(activityView: HomeContract.HomeActivityViewContract) {
        mActivityView = activityView
    }


    override fun cacheGenres() {
        mActivityView.getTmdbApi().genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Cache.cacheGenres(it.genres)
                }
    }

    override fun listUpcomingMovies() {
        mActivityView.getTmdbApi().upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1, TmdbApi.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val moviesWithGenres = it.results.map { movie ->
                        movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
                    }

                    mActivityView.showMoviesList(moviesWithGenres)
                }
    }

}