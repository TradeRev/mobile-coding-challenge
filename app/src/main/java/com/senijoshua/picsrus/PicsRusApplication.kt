package com.senijoshua.picsrus

import android.app.Application
import android.content.Context
import org.androidannotations.annotations.EApplication



@EApplication
class PicsRusApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        var appInstance: PicsRusApplication? = null

        fun getApplicationContext(): Context{
            return appInstance!!.applicationContext
        }
    }

}

