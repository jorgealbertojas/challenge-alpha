package com.example.jorge.desafiohotelurbano.api

import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiServiceInterface {


    @GET()
    @Streaming
    fun getResultsList(@Url Path :String) : Observable<Results>
    //fun getResultsList() : Observable<Results>


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