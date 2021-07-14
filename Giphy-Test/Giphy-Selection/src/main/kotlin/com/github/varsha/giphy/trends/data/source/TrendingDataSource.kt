package com.github.varsha.giphy.trends.data.source

import com.github.varsha.giphy.trends.model.TrendingResponse
import io.reactivex.Single

interface TrendingDataSource {

    fun loadTrending(): Single<TrendingResponse>
}