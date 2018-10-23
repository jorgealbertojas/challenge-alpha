package com.example.jorge.desafiohotelurbano.ui.base

/**
 * This is class is a base for all contract
 * */

class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {

    }
}