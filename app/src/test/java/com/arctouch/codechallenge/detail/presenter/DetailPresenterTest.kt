package com.arctouch.codechallenge.detail.presenter

import org.junit.Before
import org.junit.Test

class DetailPresenterTest {


    private lateinit var presenter: DetailPresenter

    @Before
    fun setup() {

        presenter = DetailPresenter()
    }


    @Test
    fun loadMovieData() {

        presenter.loadMovieData()

    }
}