package com.example.jorge.desafiohotelurbano.ui.detail

import com.example.jorge.desafiohotelurbano.models.Hotels
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailPresenter : DetailContract.Presenter {

    //private val subscriptions = CompositeDisposable()
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