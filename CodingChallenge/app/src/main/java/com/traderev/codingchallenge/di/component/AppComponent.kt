package com.traderev.codingchallenge.di.component

import com.traderev.codingchallenge.App
import com.traderev.codingchallenge.di.module.AppModule
import com.traderev.codingchallenge.di.module.BuildersModule
import com.traderev.codingchallenge.di.module.NetModule
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
