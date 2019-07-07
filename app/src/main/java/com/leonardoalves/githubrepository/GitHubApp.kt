package com.leonardoalves.githubrepository

import android.app.Application
import com.leonardoalves.githubrepository.modules.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GitHubApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startDependencies()
    }

    fun startDependencies(){
        startKoin {
            androidContext(this@GitHubApp)
        }
        Modules.init()
    }

}