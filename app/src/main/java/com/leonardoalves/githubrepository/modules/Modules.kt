package com.leonardoalves.githubrepository.modules

import org.koin.core.context.loadKoinModules

object Modules {
    fun init() = loadKoinModules(listOf(
        remoteDataSourceModule, repositoryModule, presentationModule
    ))
}