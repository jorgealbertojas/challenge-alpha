package com.example.jorge.desafiohotelurbano.di.modulo

import com.example.jorge.desafiohotelurbano.api.ApiServiceInterface
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {


    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}