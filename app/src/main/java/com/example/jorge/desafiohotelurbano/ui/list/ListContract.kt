package com.example.jorge.desafiohotelurbano.ui.list

import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.ui.base.BaseContract

class ListContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: Results)

    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun loadDataAll()
        fun orderHotel(list: Results): List<Hotels>

    }
}