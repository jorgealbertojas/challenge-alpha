package com.example.jorge.desafiohotelurbano.ui.main

import android.app.ListFragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.di.component.DaggerActivityComponent
import com.example.jorge.desafiohotelurbano.di.modulo.ActivityModule
import javax.inject.Inject


class MainActivity: AppCompatActivity(), MainContract.View {

    override fun showListFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()

        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

}
