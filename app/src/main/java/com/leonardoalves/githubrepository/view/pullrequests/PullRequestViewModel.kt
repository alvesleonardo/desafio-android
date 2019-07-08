package com.leonardoalves.githubrepository.view.pullrequests

import com.leonardoalves.githubrepository.view.custom.ViewModel

class PullRequestViewModel(val name: String,
                           val description: String,
                           val userName: String,
                           val fullName: String,
                           val userAvatar: String,
                           val url: String): ViewModel