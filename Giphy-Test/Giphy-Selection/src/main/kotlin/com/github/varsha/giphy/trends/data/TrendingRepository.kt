package com.github.varsha.giphy.trends.data

import com.github.varsha.giphy.trends.data.source.TrendingDataSource
import io.reactivex.Single

class TrendingRepository(
        private val localDataSource: TrendingDataSource,
        private val remoteDataSource: TrendingDataSource) {

    fun loadTrending() = Single.merge(
            remoteDataSource.loadTrending(),
            localDataSource.loadTrending())
}