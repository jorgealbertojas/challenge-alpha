package com.example.jorge.desafiohotelurbano.ui.main

import com.example.jorge.desafiohotelurbano.ui.base.BaseContract

/**
 *
 */
class MainContract {

    interface View: BaseContract.View {
        fun showListFragment()

    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {

    }
}