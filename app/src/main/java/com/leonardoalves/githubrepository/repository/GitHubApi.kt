package com.leonardoalves.githubrepository.repository

import com.leonardoalves.githubrepository.data.ResponseRespository
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    fun getRepositories( @Query("page") page: Int,
                         @Query("q") query: String = "language:Java",
                         @Query("sort") sortBy: String = "stars") : Flowable<ResponseRespository>
}