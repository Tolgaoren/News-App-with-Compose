package com.toren.news.presentation.news_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toren.news.common.Constants
import com.toren.news.common.Resource
import com.toren.news.data.repository.DataStoreRepository
import com.toren.news.domain.use_case.get_sport.GetSportUseCase
import com.toren.news.domain.use_case.get_top_headlines.GetTopHeadlinesUseCase
import com.toren.news.domain.use_case.query_news.QueryNewsUseCase
import com.toren.news.presentation.news_list.NewsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val getQueryNewsUseCase: QueryNewsUseCase,
    private val dataStoreRepository: DataStoreRepository,
    private val getSportUseCase: GetSportUseCase
): ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    private val _sport = mutableStateOf(NewsListState())
    val sport: State<NewsListState> = _sport

    init {
        getTopHeadlines(Constants.LANG.code)
        getQueryHistory()
        getSports()
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


    private val _history = MutableStateFlow(setOf<String>())
    val history: StateFlow<Set<String>> = _history

    fun saveQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveQuery(query)
        }
    }

    fun getQueryHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getQueryHistory().collect {
                _history.value = it
            }

        }
    }

    private fun getSports() {
        getSportUseCase().onEach { result ->
            println("result: " + result)
            when (result) {
                is Resource.Success -> {
                    _sport.value = NewsListState(news = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _sport.value = NewsListState(error = result.message ?: "An unexpected error occurred.")
                }
                is Resource.Loading -> {
                    _sport.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}