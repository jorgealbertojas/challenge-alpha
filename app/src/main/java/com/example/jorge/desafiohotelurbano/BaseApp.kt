package com.example.jorge.desafiohotelurbano

import android.app.Application
import com.example.jorge.desafiohotelurbano.di.component.ApplicationComponent
import com.example.jorge.desafiohotelurbano.di.component.DaggerApplicationComponent
import com.example.jorge.desafiohotelurbano.di.module.ApplicationModule

/**
BaseApp for all application
 **/

class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {

        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}