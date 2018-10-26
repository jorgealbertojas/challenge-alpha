package com.example.jorge.desafiohotelurbano.di.component
import com.example.jorge.desafiohotelurbano.di.module.FragmentModule
import com.example.jorge.desafiohotelurbano.ui.detail.DetailFragment
import com.example.jorge.desafiohotelurbano.ui.list.ListFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(listFragment: ListFragment)

    fun inject(detailFragment: DetailFragment)

}