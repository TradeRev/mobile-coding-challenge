package com.example.joao.photoscodechallenge

import android.app.Application
import com.example.joao.photoscodechallenge.di.Injector
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.conf.ConfigurableKodein

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyApplication: Application(), KodeinAware {

    override val kodein = ConfigurableKodein(mutable = true)

    override fun onCreate() {
        super.onCreate()
        kodein.addImport(appDependencies(), true)
    }

    private fun appDependencies(): Kodein.Module {
        return Injector(this).dependencies
    }
}