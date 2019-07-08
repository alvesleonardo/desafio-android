package com.leonardoalves.githubrepository.repository.repositories

import com.leonardoalves.githubrepository.repository.GitHubApi
import com.leonardoalves.githubrepository.repository.utils.onDefaultSchedulers

class GithubRepository(private val api: GitHubApi) {
    fun getRepositoriesList(page: Int) = api.getRepositories(page).onDefaultSchedulers().map { it.items.orEmpty() }
    fun getPullRequestList(creator: String, repository: String, page: Int) = api.getPullRequest(creator, repository, page).onDefaultSchedulers()
}