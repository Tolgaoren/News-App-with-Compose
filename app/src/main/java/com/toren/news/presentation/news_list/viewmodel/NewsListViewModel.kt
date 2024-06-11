package com.toren.news.presentation.news_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toren.news.common.Constants
import com.toren.news.common.Resource
import com.toren.news.domain.use_case.get_top_headlines.GetTopHeadlinesUseCase
import com.toren.news.domain.use_case.query_news.QueryNewsUseCase
import com.toren.news.presentation.news_list.NewsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val getQueryNewsUseCase: QueryNewsUseCase
): ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getTopHeadlines(Constants.LANG.code)
    }

    private fun getTopHeadlines(country: String) {
        getTopHeadlinesUseCase(country).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NewsListState(news = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = NewsListState(error = result.message ?: "An unexpected error occurred.")
                }
                is Resource.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchNews(query: String) {
        getQueryNewsUseCase(query).onEach { result ->
            println(result)
            when (result) {
                is Resource.Success -> {
                    _state.value = NewsListState(news = result.data ?: emptyList())
                    println(result.data)
                }
                is Resource.Error -> {
                    _state.value = NewsListState(error = result.message ?: "An unexpected error occurred.")
                }
                is Resource.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}