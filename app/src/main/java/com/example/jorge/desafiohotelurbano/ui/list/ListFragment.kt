package com.example.jorge.desafiohotelurbano.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.api.ApiServiceInterface
import com.example.jorge.desafiohotelurbano.di.component.DaggerFragmentComponent
import com.example.jorge.desafiohotelurbano.di.module.FragmentModule
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.ui.detail.DetailActivity
import com.example.jorge.desafiohotelurbano.util.AppSchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment: Fragment(), ListContract.View, ListAdapter.onItemClickListener {
    override fun showDetailFragment(hotels: Hotels) {

      /*  if (activity!!.supportFragmentManager.findFragmentByTag(DetailFragment.TAG) == null) {

            activity!!.supportFragmentManager.beginTransaction()
                .addToBackStack(ListFragment.TAG)
                .setCustomAnimations(MainActivity.AnimType.FADE.getAnimPair().first!!, MainActivity.AnimType.FADE.getAnimPair().second!!)
                .replace(R.id.frame, DetailFragment().newInstance(hotels), DetailFragment.TAG)
                .commit()
        } else {
            // Maybe an animation like shake hello text
        }
*/


        val intent = Intent(this.activity, DetailActivity::class.java)
        val args = Bundle()
        args!!.putParcelable("HOTELS_NEW", hotels  as Parcelable)
        args!!.putString("HOTELS_NEW1", "ERFGDFDDFB")
        intent.putExtras(args)
        startActivity(intent)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelable("state", recyclerView.layoutManager.onSaveInstanceState())
        outState!!.putParcelable("list",listResults)
    }

    override fun showMainTitle(list: Results, context : Context): List<Hotels> {

        val numbers: MutableList<Int> = mutableListOf()

        val res = context?.getResources()

        list.results.forEach{
            when (it.stars){

                0 -> {
                    if (it.stars !in numbers) {
                        it.mainTitle = res?.getString(R.string.string_package)!!
                        numbers.add(0)
                    }
                }
                else -> {
                    if (it.stars !in numbers) {
                        it.mainTitle = it.stars.toString() + " " + res?.getString(R.string.string_star)
                        numbers.add(it.stars)
                    }
                }
            }
        }
        return list.results
    }

    override fun loadDataSuccess(list: Results) {

        val mainTitleList = showMainTitle(list, this.context!!)

        // Order list hotel based in quantity stars
        val sortedList = presenter.orderHotel(mainTitleList)

        ListFragment.listResults = list

        var adapter = ListAdapter(this!!.activity!!, sortedList.toMutableList(), this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        if (ListFragment.stateRecyclerView != null) {
            recyclerView.layoutManager.onRestoreInstanceState(ListFragment.stateRecyclerView)
        }
        recyclerView!!.setAdapter(adapter)
    }

    override fun loadDataCacheSuccess(list: Results) {

        var adapter = ListAdapter(this!!.activity!!, list.results.toMutableList(), this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        if (ListFragment.stateRecyclerView != null) {
            recyclerView.layoutManager.onRestoreInstanceState(ListFragment.stateRecyclerView)
        }
        recyclerView!!.setAdapter(adapter)
    }



    @Inject
    lateinit var presenter: ListContract.Presenter

    private lateinit var rootView: View



    fun newInstance(): ListFragment {
        return ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_list, container, false)
        return rootView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))

        if (savedInstanceState != null) {
            ListFragment.stateRecyclerView = savedInstanceState!!.getParcelable("state")
            ListFragment.listResults = savedInstanceState!!.getParcelable("list")
            presenter.loadDataCache(ListFragment.listResults as Results)
        }else{
            initView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

/*    override fun loadDataSuccess(list: List<Results>) {
        var adapter = ListAdapter(this!!.activity!!, list.toMutableList(), this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setAdapter(adapter)

*//*        val swipeHandler = object : SwipeToDelete(activity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }*//*

       // val itemTouchHelper = ItemTouchHelper(swipeHandler)
        //itemTouchHelper.attachToRecyclerView(recyclerView)
    }*/


    override fun itemRemoveClick(postId: String) {

        TODO("not implemented")
    }

    override fun itemDetail(hotels : Hotels) {
        showDetailFragment(hotels)
        ListFragment.stateRecyclerView = recyclerView.layoutManager.onSaveInstanceState()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        var api : ApiServiceInterface? = null
        presenter.loadData(AppSchedulerProvider(), api!!.getResultsList())
    }

    companion object {
        var stateRecyclerView: Parcelable? = null
        var listResults: Parcelable? = null
        val TAG: String = "ListFragment"
    }



}