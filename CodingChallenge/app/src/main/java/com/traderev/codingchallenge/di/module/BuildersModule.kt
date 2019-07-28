package com.traderev.codingchallenge.di.module

import com.practice.gallery.view.activities.PhotoListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePhotoListActivity(): PhotoListActivity
}