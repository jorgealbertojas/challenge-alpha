package com.example.jorge.desafiohotelurbano.ui.detail

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.models.Hotels

/**
Model Adress with Parcelable
 */

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ShowDetail()

    }

    fun ShowDetail(){

        var bundle :Bundle ?= intent.bundle
        if (bundle != null) {
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

    companion object IntentOptions {
        private const val EXTRA_MESSAGE = "HOTELS_NEW"

        var Intent.bundle: Bundle?
            get() = extras
            set(bundle) {
                putExtra(EXTRA_MESSAGE, bundle)
            }



    }
}
