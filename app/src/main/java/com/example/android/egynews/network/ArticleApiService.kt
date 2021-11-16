package com.example.android.egynews.network

import com.example.android.egynews.models.ArticlesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val articleRetrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(ApiConstants.BASE_URL)
    .build()

interface ArticleApiService {

    @GET("top-headlines?country=eg&apiKey=" + ApiConstants.EGY_NEWS_KEY)
    fun getArticles(): Observable<ArticlesResponse>
}

object ArticleApi {
    val articleRetrofitService: ArticleApiService by lazy {
        articleRetrofit.create(ArticleApiService::class.java)
    }
}