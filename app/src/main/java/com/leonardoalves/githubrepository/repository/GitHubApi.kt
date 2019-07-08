package com.leonardoalves.githubrepository.repository

import com.leonardoalves.githubrepository.data.ResponsePullRequests
import com.leonardoalves.githubrepository.data.ResponseRepository
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    fun getRepositories( @Query("page") page: Int,
                         @Query("q") query: String = "language:Java",
                         @Query("sort") sortBy: String = "stars") : Flowable<ResponseRepository>

    @GET("repos/{creator}/{repository}/pulls")
    fun getPullRequest(@Path("creator") creator: String,
                       @Path("repository") repository: String,
                       @Query("page") page: Int) : Flowable<List<ResponsePullRequests>>
}