package com.leonardoalves.githubrepository.modules

import com.google.gson.GsonBuilder
import com.leonardoalves.githubrepository.repository.GitHubApi
import com.leonardoalves.githubrepository.repository.repositories.GithubRepository
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val SERVER_URL = "https://api.themoviedb.org/4/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
const val TIMEOUT = 120L

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder().baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

val remoteDataSourceModule = module {
    single { createOkHttpClient() }
    single { createWebService<GitHubApi>(get(), SERVER_URL) }
}

val repositoryModule = module {
    single { GithubRepository(get()) }
}