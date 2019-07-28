package com.traderev.codingchallenge.di.component

import com.practice.gallery.App
import com.practice.gallery.di.module.AppModule
import com.practice.gallery.di.module.BuildersModule
import com.practice.gallery.di.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidInjectionModule
    ::class), (BuildersModule::class), (AppModule::class), (NetModule::class)]
)

interface AppComponent {
    fun inject(app: App)
}
