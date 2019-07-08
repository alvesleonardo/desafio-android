package com.leonardoalves.githubrepository.view.pullrequests

import com.leonardoalves.githubrepository.view.custom.ViewModel

interface PullRequestsView {
    fun setItems(items: List<ViewModel>, resetList: Boolean)
    fun loading(loading: Boolean)
}