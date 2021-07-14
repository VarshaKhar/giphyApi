package com.github.varsha.giphy.trends

import com.github.varsha.giphy.trends.model.TrendingResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Retrofit interface for Trending API endpoint.
 */
interface TrendingApi {

    @GET("/v1/gifs/trending?api_key=1gGutWcmvuVxANQsV3aMlmlhjQwyobSg")
    fun loadTrending(): Single<TrendingResponse>
}