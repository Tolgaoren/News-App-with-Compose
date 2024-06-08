package com.toren.news.domain.use_case.get_top_headlines

import com.toren.news.common.Resource
import com.toren.news.data.remote.dto.toNews
import com.toren.news.domain.model.News
import com.toren.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val  newsRepository: NewsRepository
) {

    operator fun invoke(country: String): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading<List<News>>())
            val news = newsRepository.getTopHeadlines(country).articles.map { it.toNews()}
            emit(Resource.Success<List<News>>(news))
        } catch (e: HttpException) {
            emit(Resource.Error<List<News>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<News>>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
        }
    }

}