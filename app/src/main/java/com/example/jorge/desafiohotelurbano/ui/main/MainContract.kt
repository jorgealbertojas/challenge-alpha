package com.example.jorge.desafiohotelurbano.ui.main

import com.example.jorge.desafiohotelurbano.ui.base.BaseContract

/**
 * Main contract for support all application
 */
class MainContract {

    interface View: BaseContract.View {
        fun showListFragment()

    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {

    }
}