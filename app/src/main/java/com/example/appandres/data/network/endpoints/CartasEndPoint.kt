package com.example.appandres.data.network.endpoints

import com.example.appandres.data.network.entity.allCartas.ApiAllCartas
import retrofit2.Response
import retrofit2.http.GET

interface CartasEndPoint {
    @GET("cards")
    suspend fun getAllCartas(): Response<ApiAllCartas>
}