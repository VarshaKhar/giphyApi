package com.github.varsha.giphy.trends.data.source.local

import com.github.varsha.giphy.trends.data.source.TrendingDataSource
import com.github.varsha.giphy.trends.model.TrendingResponse
import io.reactivex.Single

class LocalTrendingDataSource : TrendingDataSource {

    override fun loadTrending() = Single.fromCallable { TrendingResponse((emptyList())) }

}