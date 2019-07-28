package com.traderev.codingchallenge.di.module

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.traderev.codingchallenge.network.PhotoService
import com.traderev.codingchallenge.viewmodel.PhotoListViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetModule::class])
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app


    @Provides
    @Singleton
    fun providePhotoListViewModel(
        factory: PhotoListViewModelFactory
    ): ViewModelProvider.Factory = factory


    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }

}
