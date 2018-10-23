package com.example.jorge.desafiohotelurbano.di.component

import com.example.jorge.desafiohotelurbano.BaseApp
import com.example.jorge.desafiohotelurbano.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}