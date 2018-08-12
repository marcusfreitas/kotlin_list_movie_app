package com.arctouch.codechallenge.repository.data

import com.arctouch.codechallenge.repository.model.Genre

object Cache {

    var genres = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        this.genres = genres
    }
}
