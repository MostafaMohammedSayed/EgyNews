package com.example.android.egynews.repository

import android.content.Context
import com.example.android.egynews.database.getArticlesDatabase
import com.example.android.egynews.models.Article
import com.example.android.egynews.models.ArticlesResponse
import com.example.android.egynews.network.ArticleApi
import com.example.android.egynews.utils.Logger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ArticlesRepository (context: Context){

    val articlesDatabase = getArticlesDatabase(context)
    val compositeDisposable = CompositeDisposable()

    fun fetchArticles(): Observable<ArticlesResponse> {

        return ArticleApi.articleRetrofitService.getArticles()
            .subscribeOn(Schedulers.io())
    }

    fun insertArticles(){
        compositeDisposable.add(
            fetchArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    Observable.fromIterable(it.articles)
                }
                .subscribeWith(object: DisposableObserver<Article>(){
                    override fun onNext(t: Article) {
                        articlesDatabase.articlesDao.insertArticle(t)
                    }

                    override fun onError(e: Throwable) {
                        Logger.debugError("Error no articles added",e)
                    }

                    override fun onComplete() {
                        Logger.debug("Finished adding articles to database")
                    }

                })
        )
    }

}