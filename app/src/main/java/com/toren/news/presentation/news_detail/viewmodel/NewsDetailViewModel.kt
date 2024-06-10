package com.toren.news.presentation.news_detail.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.toren.news.domain.model.News
import com.toren.news.presentation.news_list.NewsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(

) : ViewModel() {

    fun state(state: News) : News = state
}

