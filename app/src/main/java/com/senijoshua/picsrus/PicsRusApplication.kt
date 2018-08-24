package com.senijoshua.picsrus

import android.app.Application
import org.androidannotations.annotations.EApplication

@EApplication
class PicsRusApplication : Application() {

    val appInstance: PicsRusApplication = this

    override fun onCreate() {
        super.onCreate()
    }

    fun getAppContext() : PicsRusApplication{
        return appInstance
    }
}