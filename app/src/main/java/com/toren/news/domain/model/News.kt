package com.toren.news.domain.model

import com.toren.news.data.remote.dto.Source
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
