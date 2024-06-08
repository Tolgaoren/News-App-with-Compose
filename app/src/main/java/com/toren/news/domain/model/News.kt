package com.toren.news.domain.model

import com.toren.news.data.remote.dto.Source

data class News(
    val author: String,
    val content: Any,
    val description: Any,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: Any
)
