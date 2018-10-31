package com.example.jorge.desafiohotelurbano.ui.detail

import android.os.Bundle
import android.os.Parcelable
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : Fragment(), DetailContract.View {


    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var rootView: View

    fun newInstance(hotel: Hotels): DetailFragment {
        val args = Bundle()
        args.putParcelable("HOTELS", hotel as Parcelable)
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
        val hotel : Hotels =   arguments!!.getParcelable ("HOTELS")
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


        tv_item_body.setText(hotel.description)


        val urlHotel = hotel.gallery!![0]?.url
        Picasso.with(context).load(urlHotel).fit().centerCrop().error(R.mipmap.ic_launcher).into(iv_image_hotel)

        tv_item_description.setText(hotel.name)

        val res = context?.getResources()
        tv_price.setText(res!!.getString(R.string.string_money) + hotel.price!!.current_price?.toString())
        tv_city!!.setText(" - " + hotel.address?.city!!)
        tv_state!!.setText(" - " + hotel.address?.state!!)



        when (hotel.amenities!!.size){
            0 -> {
                tv_amenities1!!.setText(res?.getString(R.string.string_amenities))
                tv_amenities2!!.setText("")
                tv_amenities3!!.setText("")
            }
            1 -> {
                tv_amenities1!!.setText(hotel?.amenities!![0]?.name!!)
                tv_amenities2!!.setText("")
                tv_amenities3!!.setText("")
            }
            2 -> {
                tv_amenities1!!.setText(hotel?.amenities!![0]?.name!!)
                tv_amenities2!!.setText(hotel?.amenities!![1]?.name!!)
                tv_amenities3!!.setText("")
            }
            else -> {
                tv_amenities1!!.setText(hotel?.amenities!![0]?.name!!)
                tv_amenities2!!.setText(hotel?.amenities!![1]?.name!!)
                tv_amenities3!!.setText(hotel?.amenities!![2]?.name!!)
            }
        }

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