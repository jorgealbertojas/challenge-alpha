package com.example.jorge.desafiohotelurbano.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.util.Pair
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.di.component.DaggerActivityComponent
import com.example.jorge.desafiohotelurbano.di.module.ActivityModule
import com.example.jorge.desafiohotelurbano.ui.detail.DetailFragment
import com.example.jorge.desafiohotelurbano.ui.list.ListFragment
import javax.inject.Inject


class MainActivity: AppCompatActivity(), MainContract.View {

    override fun showListFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first!!, AnimType.SLIDE.getAnimPair().second!!)
            .replace(R.id.frame, ListFragment().newInstance(), ListFragment.TAG)
            .commit()
    }

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(DetailFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    enum class AnimType() {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            when(this) {
                SLIDE -> return Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> return Pair(R.anim.fade_in, R.anim.fade_out)
            }

            return Pair(R.anim.slide_left, R.anim.slide_right)
        }
    }

}
