package com.arctouch.codechallenge.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.detail.contract.DetailContract
import com.arctouch.codechallenge.detail.presenter.DetailPresenter
import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.model.Movie
import com.arctouch.codechallenge.shared.AppDelegate
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.DetailActivityViewContract {


    companion object {
        const val MOVIE_ID_KEY = "movie_id_key"
    }

    @Inject
    lateinit var api: TmdbApi

    @Inject
    lateinit var presenter: DetailPresenter

    private val movieImageUrlBuilder = MovieImageUrlBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        inject()

        val movieId = intent.getIntExtra(MOVIE_ID_KEY, -1)

        presenter.attachActivityView(this)
        presenter.loadMovieData(movieId.toLong())

    }

    private fun inject() {
        (application as AppDelegate).injector?.injectDetailActivity(this)
    }

    override fun getTmdbApi(): TmdbApi {
        return api
    }

    override fun showMovieData(movie: Movie) {
        Glide.with(this)
                .load(movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(posterImageView)

        titleTextView.text = movie.title
        genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
        releaseDateTextView.text = movie.releaseDate

        overviewTextView.text = movie.overview

        Glide.with(this)
                .load(movie.backdropPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(backdropImageView)
    }

}