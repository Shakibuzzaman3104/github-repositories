package com.shakibuzzaman.githubrepos.api

import com.shakibuzzaman.githubrepos.model.ModelRepositoryResponse
import retrofit2.http.GET

interface RepositoryApi {
    @GET("search/repositories?q=Android&per_page=50&sort=stars")
    suspend fun getTopRepositories(): ModelRepositoryResponse
}