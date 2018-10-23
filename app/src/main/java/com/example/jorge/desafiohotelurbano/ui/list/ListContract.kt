package com.example.jorge.desafiohotelurbano.ui.list

import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.ui.base.BaseContract

class ListContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Results>)

    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun loadDataAll()

    }
}