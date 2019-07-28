package com.traderev.codingchallenge

import android.app.Activity
import android.app.Application
import com.traderev.codingchallenge.di.component.DaggerAppComponent
import com.traderev.codingchallenge.di.module.AppModule
import com.traderev.codingchallenge.di.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

// Extends application class to configure dependency injection
class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        @Suppress("DEPRECATION")
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(BuildConfig.UNSPLASH_BASE_URL))
            .build()
            .inject(this)


    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}