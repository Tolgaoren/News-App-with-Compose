package com.toren.news.data.repository

import com.toren.news.data.remote.api.NewsApi
import com.toren.news.data.remote.dto.NewsDto
import com.toren.news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {
    override suspend fun getTopHeadlines(country: String): NewsDto {
        return newsApi.getTopHeadlines(country)
    }

    override suspend fun getBusiness(): NewsDto {
        return newsApi.getBusiness()
    }

    override suspend fun getSports(): NewsDto {
        return newsApi.getSports()
    }

    override suspend fun getTechnology(): NewsDto {
        return newsApi.getTechnology()
    }

    override suspend fun getHealth(): NewsDto {
        return newsApi.getHealth()
    }

    override suspend fun getScience(): NewsDto {
        return newsApi.getScience()
    }

    override suspend fun getQuery(query: String): NewsDto {
        return newsApi.getQuery(query)
    }

    override suspend fun getSource(source: String): NewsDto {
        return newsApi.getSource(source)
    }
}