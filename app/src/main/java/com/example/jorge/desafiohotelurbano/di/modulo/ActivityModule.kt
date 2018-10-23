package com.example.jorge.desafiohotelurbano.di.modulo

import android.app.Activity
import com.example.jorge.desafiohotelurbano.ui.main.MainContract
import com.example.jorge.desafiohotelurbano.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}