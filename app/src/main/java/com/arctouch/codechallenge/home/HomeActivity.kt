package com.arctouch.codechallenge.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.detail.DetailActivity
import com.arctouch.codechallenge.home.contract.HomeContract
import com.arctouch.codechallenge.home.presenter.HomePresenter
import com.arctouch.codechallenge.repository.api.TmdbApi
import com.arctouch.codechallenge.repository.model.Movie
import com.arctouch.codechallenge.shared.AppDelegate
import kotlinx.android.synthetic.main.home_activity.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.HomeActivityViewContract {

    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var api: TmdbApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        inject()

        presenter.attachActivityView(this)
        presenter.cacheGenres()
        presenter.listUpcomingMovies()
    }

    private fun inject() {
        (application as AppDelegate).injector?.injectMainActivity(this)
    }

    override fun getTmdbApi(): TmdbApi {
        return api
    }

    override fun showMoviesList(movieList: List<Movie>) {
        val adapter = HomeAdapter(movieList)
        adapter.listener = object : HomeAdapter.OnItemClickListener {
            override fun onClick(movie: Movie) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_ID_KEY, movie.id)
                startActivity(intent)
            }
        }

        recyclerView.adapter = adapter
        progressBar.visibility = View.GONE
    }
}
