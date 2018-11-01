package com.example.jorge.desafiohotelurbano.ui.detail

import com.example.jorge.desafiohotelurbano.models.Hotels

class DetailPresenter : DetailContract.Presenter {

    private lateinit var view: DetailContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun loadMessage(hotels: Hotels) {
        view.loadMessageSuccess(hotels)

    }
}