package com.leonardoalves.githubrepository.modules

import com.leonardoalves.githubrepository.view.pullrequests.PullRequestsPresenter
import com.leonardoalves.githubrepository.view.pullrequests.PullRequestsView
import com.leonardoalves.githubrepository.view.repositories.RepositoriesPresenter
import com.leonardoalves.githubrepository.view.repositories.RepositoriesView
import org.koin.dsl.module

val presentationModule = module {
    factory { (view: RepositoriesView) -> RepositoriesPresenter(view, get()) }
    factory { (view: PullRequestsView) -> PullRequestsPresenter(view, get()) }
}