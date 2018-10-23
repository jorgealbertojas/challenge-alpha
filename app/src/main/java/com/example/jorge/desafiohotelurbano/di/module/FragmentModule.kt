package com.example.jorge.desafiohotelurbano.di.module

import com.example.jorge.desafiohotelurbano.api.ApiServiceInterface
import com.example.jorge.desafiohotelurbano.ui.list.ListContract
import com.example.jorge.desafiohotelurbano.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {





    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}