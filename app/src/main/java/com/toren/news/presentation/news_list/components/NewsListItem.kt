package com.toren.news.presentation.news_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.toren.news.domain.model.News

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsListItem(
    news: News,
    onItemClick: (News) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(news) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            GlideImage(
                model = news.urlToImage,
                contentDescription = null
            )

            Text(
                text = news.title
            )
            Text(
                text = news.publishedAt,
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
