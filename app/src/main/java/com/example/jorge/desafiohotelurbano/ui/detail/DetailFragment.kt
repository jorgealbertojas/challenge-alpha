package com.example.jorge.desafiohotelurbano.ui.detail

import java.io.Serializable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.di.component.DaggerFragmentComponent
//import com.example.jorge.desafiohotelurbano.di.component.DaggerFragmentComponent
import com.example.jorge.desafiohotelurbano.di.module.FragmentModule
import com.example.jorge.desafiohotelurbano.models.Hotels
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : Fragment(), DetailContract.View {


    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var rootView: View

    fun newInstance(hotel: Hotels): DetailFragment {
        val args = Bundle()
        args.putSerializable("HOTELS", hotel as Serializable)
        val fragment = DetailFragment()
        fragment.arguments = args

        return fragment
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_detail, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        val hotel =   arguments!!.getSerializable("HOTELS") as Hotels
        initView(hotel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            pb_progressBar.visibility = View.VISIBLE
        } else {
            pb_progressBar.visibility = View.GONE
        }
    }

    override fun loadMessageSuccess(hotel: Hotels) {
        tv_item_description.text = hotel.description
        tv_item_description.visibility = View.VISIBLE
    }

    private fun injectDependency() {
        val detailComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        detailComponent.inject(this)
    }

    private fun initView(hotel: Hotels) {
        presenter.loadMessage(hotel)
    }

    companion object {
        val TAG: String = "DetailFragment"

    }
}