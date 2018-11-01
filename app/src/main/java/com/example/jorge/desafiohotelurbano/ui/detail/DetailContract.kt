package com.example.jorge.desafiohotelurbano.ui.detail

import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.ui.base.BaseContract

class DetailContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadMessageSuccess(hotel: Hotels)
    }

    interface Presenter: BaseContract.Presenter<View> {
           fun loadMessage(hotel: Hotels)
    }
}