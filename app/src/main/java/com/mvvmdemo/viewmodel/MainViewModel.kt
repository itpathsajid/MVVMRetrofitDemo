package com.mvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmdemo.model.QuoteList
import com.mvvmdemo.model.Result
import com.mvvmdemo.reposotory.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotesAPi(1)
        }
    }

    val quotes: LiveData<QuoteList>
    get() = quoteRepository.quotes
}