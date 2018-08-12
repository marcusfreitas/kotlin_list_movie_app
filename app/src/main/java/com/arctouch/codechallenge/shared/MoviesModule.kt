package com.arctouch.codechallenge.shared

import com.arctouch.codechallenge.detail.presenter.DetailPresenter
import com.arctouch.codechallenge.home.presenter.HomePresenter
import com.arctouch.codechallenge.repository.api.TmdbApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class MoviesModule {


    @Provides
    @Singleton
    fun providesHomePresenter(): HomePresenter {
        return HomePresenter()
    }

    @Provides
    @Singleton
    fun providesDetailPresenter(): DetailPresenter {
        return DetailPresenter()
    }


    @Provides
    @Singleton
    internal fun providesTmdbApi() : TmdbApi = Retrofit.Builder()
            .baseUrl(TmdbApi.URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TmdbApi::class.java)
}