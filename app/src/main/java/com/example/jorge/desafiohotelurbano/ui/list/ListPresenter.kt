package com.example.jorge.desafiohotelurbano.ui.list


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.jorge.desafiohotelurbano.api.ApiServiceInterface
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
presenter List get ListContract Presenter
 **/

class ListPresenter @Inject constructor(var api : ApiServiceInterface): ListContract.Presenter {
    override fun loadDataCache(list: Results) {
        view.loadDataCacheSuccess(list)
    }
    private val subscriptions = CompositeDisposable()
    private lateinit var view: ListContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListContract.View) {
        this.view = view
    }

    // Verify connection internet
    override fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    // Get service Json
    override fun loadData(scheduler : SchedulerProvider, url : String)  {

        var subscribe =  api.getResultsList(url).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({ results ->
                view.showProgress(false)
                view.loadDataSuccess(results)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    // Order Hotel for major stars
    override fun orderHotel(list: List<Hotels>): List<Hotels> {
        return  list.sortedWith (compareByDescending({ it.stars }))
    }


}