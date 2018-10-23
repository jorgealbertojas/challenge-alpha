package com.example.jorge.desafiohotelurbano.di.module

import android.app.Application
import com.example.jorge.desafiohotelurbano.di.scope.PerApplication
import com.example.jorge.desafiohotelurbano.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}