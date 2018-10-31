package com.example.jorge.desafiohotelurbano.ui.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.example.jorge.desafiohotelurbano.ui.list.ListFragment
import com.example.jorge.desafiohotelurbano.ui.main.MainActivity


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent1: Intent =  getIntent()
        var bundle :Bundle ?=intent1.extras
        if (bundle != null) {
            val hotels1 : String = bundle!!.getString("HOTELS_NEW1")
            val hotels : Parcelable = bundle!!.getParcelable("HOTELS_NEW")
            if (this!!.supportFragmentManager.findFragmentByTag(DetailFragment.TAG) == null) {

                this!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.frame1, DetailFragment().newInstance(hotels as Hotels), DetailFragment.TAG)
                    .commit()
            }
        }else{
            this.finish()
        }
    }

    override fun onBackPressed() {
        this.finish()
    }
}
