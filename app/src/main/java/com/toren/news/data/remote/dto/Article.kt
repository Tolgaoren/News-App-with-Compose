package com.toren.news.data.remote.dto

import com.toren.news.domain.model.News

data class Article(
    val author: String?,
    val content: Any?,
    val description: Any?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: Any?
)


fun Article.toNews(): News {
    return News(
        author ?: "",
        content.toString() ?: "",
        description.toString() ?: "",
        publishedAt ?: "",
        source?.name ?: "",
        title ?: "",
        url ?: "",
        urlToImage.toString() ?: ""
    )
}