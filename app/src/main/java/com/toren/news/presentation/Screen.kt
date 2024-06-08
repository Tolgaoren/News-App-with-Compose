package com.toren.news.presentation

sealed class Screen(val route: String) {
    object NewsListScreen: Screen("news_list_screen")
    object NewsDetailScreen: Screen("news_detail_screen")
}