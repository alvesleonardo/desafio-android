package com.leonardoalves.githubrepository.view.repositories

import com.leonardoalves.githubrepository.view.custom.ViewModel

class RepositoryViewModel(
    val repositoryId: Int,
    val ownerUserName : String,
    val ownerFullName : String,
    val ownerAvatar : String,
    val repositoryName : String,
    val repositoryDescription: String,
    val repositoryStars: Int,
    val repositoryForks: Int
) : ViewModel