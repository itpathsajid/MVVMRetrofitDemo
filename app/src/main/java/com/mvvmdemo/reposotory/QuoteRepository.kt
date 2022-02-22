package com.mvvmdemo.reposotory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmdemo.api.QuoteService
import com.mvvmdemo.model.QuoteList

class QuoteRepository(private val quoteService: QuoteService) {

    private val quoteListData = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
    get() = quoteListData

    suspend fun getQuotesAPi(page: Int) {
        val result = quoteService.getQuoteList(page)
        if (result.body() != null){
            quoteListData.postValue(result.body())
        }
    }
}