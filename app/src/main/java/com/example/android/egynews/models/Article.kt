package com.example.android.egynews.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity()
data class Article(
    @SerializedName("source")
    @PrimaryKey
    val source: Source,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishTime: String?
)