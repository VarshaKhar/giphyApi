package com.github.varsha.giphy.trends

import com.github.varsha.giphy.trends.model.TrendingResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SearchApi {
    @GET("/v1/gifs/search?api_key=1gGutWcmvuVxANQsV3aMlmlhjQwyobSg")
    fun loadTrending(): Single<TrendingResponse>
}