package com.example.jorge.desafiohotelurbano.ui.list

import android.content.Context
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.ui.base.BaseContract
import com.example.jorge.desafiohotelurbano.util.SchedulerProvider

/**
Contract List get BaseContract View
 **/

class ListContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showMainTitle(list: Results,  context : Context): List<Hotels>
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: Results)
        fun loadDataCacheSuccess(list: Results)
        fun showDetailFragment(hotels: Hotels)

    }

    interface Presenter: BaseContract.Presenter<View> {
        fun isNetworkAvailable(context: Context): Boolean
        fun loadData(scheduler : SchedulerProvider, url : String)
        fun loadDataCache(list: Results)
        fun orderHotel(list: List<Hotels>): List<Hotels>
    }
}