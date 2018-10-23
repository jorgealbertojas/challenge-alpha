package com.example.jorge.desafiohotelurbano.di.component

import android.app.ListFragment
import com.example.jorge.desafiohotelurbano.di.modulo.FragmentModule
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(listFragment: ListFragment)

}