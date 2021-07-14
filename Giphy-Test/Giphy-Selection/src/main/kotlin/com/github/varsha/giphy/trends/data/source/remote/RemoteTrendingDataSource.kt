package com.github.varsha.giphy.trends.data.source.remote

import com.github.varsha.giphy.trends.TrendingApi
import com.github.varsha.giphy.trends.data.source.TrendingDataSource

class RemoteTrendingDataSource(private val api: TrendingApi) : TrendingDataSource {

    override fun loadTrending() = api.loadTrending()

}