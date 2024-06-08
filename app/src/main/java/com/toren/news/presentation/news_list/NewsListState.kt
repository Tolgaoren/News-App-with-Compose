package com.toren.news.presentation.news_list

import com.toren.news.domain.model.News

data class NewsListState(
    val isLoading: Boolean = false,
    val news: List<News> = emptyList(),
    val error: String = ""
)
