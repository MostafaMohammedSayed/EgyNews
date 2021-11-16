package com.example.android.egynews.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android.egynews.R

class ArticlesActivity : AppCompatActivity() {
    private lateinit var viewModel: ArticlesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        setUpViewModel()
        startArticlesObserver()
        fetchUiData()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            ArticlesViewModel.ArticlesViewModelFactory(application)
        )[ArticlesViewModel::class.java]

    }

    private fun fetchUiData() {
        viewModel.fetchArticlesData()
    }

    private fun startArticlesObserver() {
        viewModel.observeOnGettingArticles(
            owner = this,
            observer = {
                //check on state
                Log.i("ArticlesActivity", it.value?.size.toString())
                Log.i("ArticlesActivity", it.throwable?.message.toString())
            })
    }
}