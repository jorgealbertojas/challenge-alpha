package com.example.jorge.desafiohotelurbano.ui.list

import com.example.jorge.desafiohotelurbano.api.ApiServiceInterface
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.models.Results
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListPresenter : ListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ListContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListContract.View) {
        this.view = view
    }

    override fun loadData() {
        var subscribe = api.getResultsList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ results ->
                view.showProgress(false)
                view.loadDataSuccess(results)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun orderHotel(list: Results): List<Hotels> {
        return  list.results.sortedWith(compareBy({ it.stars }))
    }

    override fun loadDataAll() {
/*        var subscribe = Observable.zip(api.getPostList(), api.getUserList(), api.getAlbumList(),
            Function3<List<Post>, List<User>, List<Album>, DetailsViewModel> {
                posts, users, albums ->
                createDetailsViewModel(posts, users, albums)
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ model: DetailsViewModel? ->
                view.showProgress(false)
                view.loadDataAllSuccess(model!!)
            },{ error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)*/
    }

/*    private fun createDetailsViewModel(posts: List<Post>, users: List<User>, albums: List<Album>): DetailsViewModel {
        val postList = posts.take(30)
        val userList = users.take(30)
        val albumList = albums.take(30)
        return DetailsViewModel(postList, userList, albumList)
    }

    override fun deleteItem(item: Post) {
        //api.deleteUser(item.id)
    }*/
}