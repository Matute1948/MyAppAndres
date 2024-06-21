package com.example.appandres.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {
    //API CLASH
    fun returnBaseRetrofitClash(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.clashroyale.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClienteClash())
            .build()
    }

    private fun apiClienteClash() : OkHttpClient = OkHttpClient.Builder().addInterceptor(ClashInterceptor(ApiKeys.API_CLASH)).build()

}