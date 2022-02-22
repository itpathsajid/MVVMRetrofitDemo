package com.mvvmdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvmdemo.R
import com.mvvmdemo.api.QuoteService
import com.mvvmdemo.api.RetrofitHelper
import com.mvvmdemo.databinding.ActivityMainBinding
import com.mvvmdemo.factory.MainViewModelFactory
import com.mvvmdemo.reposotory.QuoteRepository
import com.mvvmdemo.ui.adapter.TestAdapter
import com.mvvmdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private val testAdapter = TestAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = testAdapter


        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = QuoteRepository(quoteService)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.quotes.observe(this) { it ->
            it?.let { list ->
                testAdapter.submitList(list.results)
            }
        }
    }
}