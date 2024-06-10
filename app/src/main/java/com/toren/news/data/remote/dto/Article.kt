package com.toren.news.data.remote.dto

import com.toren.news.domain.model.News
import com.toren.news.util.TimeFormatter

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)


fun Article.toNews(): News {
    return News(
        author ?: "",
        content ?: "",
        description ?: "",
        TimeFormatter.formatDateTime(publishedAt),
        source?.name ?: "",
        title ?: "",
        url ?: "",
        urlToImage ?: ""
    )
}