package com.arctouch.codechallenge.home.presenter

import com.arctouch.codechallenge.home.contract.HomeContract
import com.arctouch.codechallenge.home.presenter.HomePresenter
import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.model.GenreResponse
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable

class HomePresenterTest {


    private lateinit var apiMock: TmdbApi
    private lateinit var activityViewMock : HomeContract.HomeActivityViewContract
    private lateinit var presenter: HomePresenter

    @Before
    fun setup() {
        apiMock = mock()
        activityViewMock = mock()
        presenter = HomePresenter()
        presenter.attachActivityView(activityViewMock)
    }

    @Test
    fun cacheGenres() {

        val observableMock = mock<Observable<GenreResponse>>()

        whenever(apiMock.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)).thenReturn(observableMock)

        whenever(activityViewMock.getTmdbApi()).thenReturn(apiMock)

        presenter.cacheGenres()

        verify(activityViewMock, times(1)).getTmdbApi()
    }

    @Test
    fun listUpcomingMovies() {
        val observableMock = mock<Observable<GenreResponse>>()

        whenever(apiMock.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)).thenReturn(observableMock)

        presenter.listUpcomingMovies()

        verify(activityViewMock).getTmdbApi()

        verify(activityViewMock).showMoviesList(any())
    }
}