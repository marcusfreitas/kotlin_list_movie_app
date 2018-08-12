package com.arctouch.codechallenge.shared

import android.app.Application
import com.arctouch.codechallenge.detail.DetailActivity
import com.arctouch.codechallenge.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

class AppDelegate : Application() {

    @Singleton
    @Component(modules = [MoviesModule::class])
    interface AppInjector {

        fun injectMainActivity(activity: HomeActivity)
        fun injectDetailActivity(activity: DetailActivity)
    }

    var injector: AppInjector? = null

    override fun onCreate() {
        super.onCreate()

        injector = DaggerAppDelegate_AppInjector.builder().build()
    }
}