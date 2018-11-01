package com.example.jorge.desafiohotelurbano.di.module

import android.app.Activity
import com.example.jorge.desafiohotelurbano.ui.main.MainContract
import com.example.jorge.desafiohotelurbano.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
modulo for support Test
 */

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