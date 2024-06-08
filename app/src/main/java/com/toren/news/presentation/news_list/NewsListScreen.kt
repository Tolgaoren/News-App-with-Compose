package com.toren.news.presentation.news_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.toren.news.presentation.Screen
import com.toren.news.presentation.news_list.components.NewsListItem
import com.toren.news.presentation.news_list.viewmodel.NewsListViewModel

@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.news) { news ->
                NewsListItem(
                    news = news,
                    onItemClick = {
                        navController.navigate(
                            Screen.NewsDetailScreen.route + "/$news")
                    }
                )
            }
        }
        if(state.error.isNotBlank()) {
            Text(text = state.error, color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth().align(Alignment.Center))

        }

    }
}