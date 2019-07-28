package com.practice.gallery

import android.app.Activity
import android.app.Application
import com.practice.gallery.di.component.DaggerAppComponent
import com.practice.gallery.di.module.AppModule
import com.practice.gallery.di.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


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