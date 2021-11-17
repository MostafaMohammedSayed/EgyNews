package com.example.android.egynews.database

import android.content.Context
import androidx.room.*
import com.example.android.egynews.models.Article
import com.example.android.egynews.models.Source
import io.reactivex.rxjava3.core.Observable

@Dao
interface ArticlesDao {
    @Query("Select * From article")
    fun getArticles(): Observable<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)
}

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract val articlesDao: ArticlesDao
}

lateinit var ARTICLES_DATABASE_INSTANCE: ArticlesDatabase

fun getArticlesDatabase(context: Context): ArticlesDatabase {
    synchronized(ArticlesDatabase::class.java) {
        if (!::ARTICLES_DATABASE_INSTANCE.isInitialized) {
            ARTICLES_DATABASE_INSTANCE =
                Room.databaseBuilder(context, ArticlesDatabase::class.java, "articles.db")
                    .build()
        }
    }
    return ARTICLES_DATABASE_INSTANCE
}