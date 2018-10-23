package com.example.jorge.desafiohotelurbano.di.component

import com.example.jorge.desafiohotelurbano.di.module.ActivityModule
import com.example.jorge.desafiohotelurbano.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}