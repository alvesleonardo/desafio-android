package com.leonardoalves.githubrepository.view.repositories

import com.leonardoalves.githubrepository.repository.repositories.GithubRepository
import io.reactivex.disposables.CompositeDisposable

class RepositoriesPresenter(
    private val repositoriesView: RepositoriesView,
    private val githubRepository: GithubRepository
) {

    private val compositeDisposable = CompositeDisposable()

    private var page = 1
    private var loading = false
    private var completed = false

    fun getRepositoryList() {
        compositeDisposable.add(
            githubRepository.getRepositoriesList(page)
                .map { repositories ->
                    repositories
                        .filter { it?.id != null }
                        .map {
                            RepositoryViewModel(
                                it!!.id,
                                it.owner?.login ?: "",
                                it.fullName ?: "",
                                it.owner?.avatarUrl ?: "",
                                it.name ?: "",
                                it.description ?: "",
                                it.stargazersCount ?: 0,
                                it.forksCount
                            )
                        }
                }
                .doOnSubscribe {
                    loading = true
                    repositoriesView.loading(loading)
                }
                .doOnComplete {
                    loading = false
                    repositoriesView.loading(loading)
                }
                .subscribe({
                    repositoriesView.setItems(it, page == 1)
                    page++
                }, {
                    loading = false
                    it.printStackTrace()
                }, {})
        )
    }

    fun onScrollBeyond() {
        if (!loading || !completed) {
            getRepositoryList()
        }
    }

    fun onRefresh() {
        page = 1
        getRepositoryList()
    }
}