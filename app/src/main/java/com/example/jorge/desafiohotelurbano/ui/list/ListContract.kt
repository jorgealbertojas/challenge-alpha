package com.example.jorge.desafiohotelurbano.ui.list

import android.content.Context
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.ui.base.BaseContract
import com.example.jorge.desafiohotelurbano.util.SchedulerProvider
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.Scheduler

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

        fun loadData(scheduler : SchedulerProvider, url : String)
        fun loadDataCache(list: Results)
        fun loadDataAll()
        fun orderHotel(list: List<Hotels>): List<Hotels>
        //fun loadDetailData(hotel: Hotels,  context : Context)

    }
}