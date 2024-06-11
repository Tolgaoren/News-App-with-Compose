package com.toren.news.data.remote.api

import com.toren.news.common.Resource
import com.toren.news.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String, @Query("apiKey") apiKey: String= "6a0e6799f91f45da8bbce836ba9a89d5"): NewsDto

    @GET("/v2/top-headlines")
    suspend fun getQuery(@Query ("q") query: String, @Query("apiKey") apiKey: String= "6a0e6799f91f45da8bbce836ba9a89d5"): NewsDto

    @GET("/v2/top-headlines?country=tr&category=business&apiKey=6a0e6799f91f45da8bbce836ba9a89d5")
    suspend fun getBusiness(): NewsDto

    @GET("/v2/top-headlines?country=tr&category=sports&apiKey=6a0e6799f91f45da8bbce836ba9a89d5")
    suspend fun getSports(): NewsDto

    @GET("/v2/top-headlines?country=tr&category=technology&apiKey=6a0e6799f91f45da8bbce836ba9a89d5")
    suspend fun getTechnology(): NewsDto

    @GET("/v2/top-headlines?country=tr&category=health&apiKey=6a0e6799f91f45da8bbce836ba9a89d5")
    suspend fun getHealth(): NewsDto

    @GET("/v2/top-headlines?country=tr&category=science&apiKey=6a0e6799f91f45da8bbce836ba9a89d5")
    suspend fun getScience(): NewsDto

    @GET("/v2/top-headlines?sources={source}-news&apiKey=6a0e6799f91f45da8bbce836ba9a89d5")
    suspend fun getSource(@Query("source") source: String): NewsDto

}