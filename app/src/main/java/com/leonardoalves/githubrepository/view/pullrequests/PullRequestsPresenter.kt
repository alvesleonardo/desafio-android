package com.leonardoalves.githubrepository.view.pullrequests

import android.content.Intent
import com.leonardoalves.githubrepository.repository.repositories.GithubRepository
import io.reactivex.disposables.CompositeDisposable

const val PULL_REQUEST_CREATOR_EXTRA = "pull_request_creator_extra"
const val PULL_REQUEST_REPOSITORY_EXTRA = "pull_request_repository_extra"

class PullRequestsPresenter(
    private val pullRequestsView: PullRequestsView,
    private val githubRepository: GithubRepository
) {

    private val compositeDisposable = CompositeDisposable()

    private var page = 1
    private var loading = false
    private var completed = false

    private var creator: String? = null
    private var repository: String? = null

    fun onCreate(intent: Intent){
        creator = intent.getStringExtra(PULL_REQUEST_CREATOR_EXTRA)
        repository = intent.getStringExtra(PULL_REQUEST_REPOSITORY_EXTRA)
        getPullRepositoryList()
    }

    private fun getPullRepositoryList() {
        compositeDisposable.add(
            githubRepository.getPullRequestList(creator!!, repository!!, page)
                .map { pullRequests ->
                    pullRequests.map {
                        PullRequestViewModel(it.title?:"", it.body?:"", it.user?.login?:"", "", it.user?.avatarUrl?:"", it.htmlUrl?:"http://github.com/$creator/$repository")
                    }
                }
                .doOnSubscribe { loading = true; pullRequestsView.loading(loading) }
                .doOnComplete { loading = false; pullRequestsView.loading(loading) }
                .subscribe({
                    pullRequestsView.setItems(it, page==1)
                    page++
                }, {
                    loading = false
                    completed = false
                    it.printStackTrace()
                }, {})
        )
    }

    fun onScrollBeyond() {
        if (!loading && !completed){
            getPullRepositoryList()
        }
    }

    fun onRefresh() {
        page = 1
        completed = false
        loading = false
        compositeDisposable.clear()
        getPullRepositoryList()
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}