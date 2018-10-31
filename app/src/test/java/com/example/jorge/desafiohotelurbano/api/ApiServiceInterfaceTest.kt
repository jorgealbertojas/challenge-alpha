package com.example.jorge.desafiohotelurbano.api

import com.example.jorge.desafiohotelurbano.models.Results
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterfaceTest {

    @GET("api?q=Rio%20de%20Janeiro")
    fun   getResultsList(): Observable<Results>

    companion object Factory {
        fun create( baseUrl :String): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}