package com.leonardoalves.githubrepository.view.repositories

interface RepositoriesView {
    fun setItems(items: List<RepositoryViewModel>, resetList: Boolean)
}