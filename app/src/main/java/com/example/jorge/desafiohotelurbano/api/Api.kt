package com.example.jorge.desafiohotelurbano.api

import com.example.jorge.desafiohotelurbano.models.Results
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("api?q=Rio%20de%20Janeiro")
    fun   getResultsList(): Observable<Results>
}