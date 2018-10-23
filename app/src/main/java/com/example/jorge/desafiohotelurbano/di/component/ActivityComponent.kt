package com.example.jorge.desafiohotelurbano.di.component

import com.example.jorge.desafiohotelurbano.di.modulo.ActivityModule
import com.example.jorge.desafiohotelurbano.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}