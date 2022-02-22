package com.mvvmdemo.api

import com.mvvmdemo.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sajid.
 */
interface QuoteService {
    @GET("/quotes")
    suspend fun getQuoteList(@Query("page") page: Int): Response<QuoteList>
}