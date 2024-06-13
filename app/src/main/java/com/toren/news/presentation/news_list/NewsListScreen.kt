package com.toren.news.presentation.news_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.toren.news.presentation.news_list.components.NewsListItem
import com.toren.news.presentation.news_list.components.NewsListItemHorizontal
import com.toren.news.presentation.news_list.viewmodel.NewsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel(),
) {
    val news = viewModel.state.value
    val sportNews = viewModel.sport.value
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory by viewModel.history.collectAsState()
    val columnState = rememberLazyListState()
    val columnSnapBehavior = rememberSnapFlingBehavior(lazyListState = columnState)

    val lazyRowState = rememberLazyListState()
    val rowSnapBehavior = rememberSnapFlingBehavior(lazyListState = lazyRowState)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = columnState,
            flingBehavior = columnSnapBehavior
            ) {

            item {
                DockedSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 4.dp),
                    query = text,
                    onQueryChange = {
                        text = it
                    },
                    onSearch = {
                        if (text.trim().isNotEmpty()) {
                            viewModel.searchNews(it)
                            viewModel.saveQuery(it)
                            active = false
                        }
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                        if (!active) {
                            text = ""
                        }
                    },
                    placeholder = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                    }
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                ) {
                    searchHistory.forEach {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    text = it
                                },
                        )
                    }
                }
            }

            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Top News",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }

            item {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        state = lazyRowState,
                        flingBehavior = rowSnapBehavior
                    ) {
                        items(news.news) { news ->
                            NewsListItemHorizontal(
                                news = news,
                                onItemClick = {
                                    navController.navigate(news)
                                }
                            )
                        }
                    }
                }
            }

            items(sportNews.news) { news ->
                NewsListItem(
                    news = news,
                    onItemClick = {
                        println(it)
                        navController.navigate(news)
                    }
                )
            }
        }
        if (sportNews.error.isNotBlank()) {
            Text(
                text = news.error, color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        if (sportNews.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}