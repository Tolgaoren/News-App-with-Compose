package com.toren.news.domain.repository

import com.toren.news.data.remote.dto.NewsDto

interface NewsRepository {

    suspend fun getTopHeadlines(country: String): NewsDto

    suspend fun getBusiness(): NewsDto

    suspend fun getSports(): NewsDto

    suspend fun getTechnology(): NewsDto

    suspend fun getHealth(): NewsDto

    suspend fun getScience(): NewsDto

    suspend fun getQuery(query: String): NewsDto

    suspend fun getSource(source: String): NewsDto
}