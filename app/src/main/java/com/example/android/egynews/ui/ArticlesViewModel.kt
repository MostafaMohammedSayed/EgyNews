package com.example.android.egynews.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.android.egynews.base.Resource
import com.example.android.egynews.models.Article
import com.example.android.egynews.models.ArticlesResponse
import com.example.android.egynews.repository.ArticlesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = ArticlesRepository(application)
    private val compositeDisposable = CompositeDisposable()
    private val articlesLiveData = MutableLiveData<Resource<List<Article>>>()

    fun observeOnGettingArticles(
        owner: LifecycleOwner,
        observer: Observer<Resource<List<Article>>>
    ) {
        articlesLiveData.observe(owner, observer)
    }

    fun fetchArticlesData() {
        articlesLiveData.value = Resource.loading()

        compositeDisposable.add(
            repo.fetchArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArticlesResponse>() {
                    override fun onNext(articles: ArticlesResponse) {
                        articlesLiveData.value = Resource.success(articles.articles)
                    }

                    override fun onError(e: Throwable) {
                        articlesLiveData.value = Resource.error(e)
                    }

                    override fun onComplete() {
                    }

                })
        )

    }

    class ArticlesViewModelFactory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArticlesViewModel::class.java)) {
                return ArticlesViewModel(application) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}