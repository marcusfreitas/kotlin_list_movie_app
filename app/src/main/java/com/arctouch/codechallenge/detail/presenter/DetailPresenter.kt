package com.arctouch.codechallenge.detail.presenter

import com.arctouch.codechallenge.detail.contract.DetailContract
import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.data.Cache
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter : DetailContract.DetailPresenterContract {

    private lateinit var mActivityView: DetailContract.DetailActivityViewContract

    override fun attachActivityView(activityView: DetailContract.DetailActivityViewContract) {
        mActivityView = activityView
    }


    override fun loadMovieData(movieId: Long) {
        mActivityView.getTmdbApi().movie(movieId, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mActivityView.showMovieData(it)
                }
    }


}