package com.example.appandres.logic.userCase

import android.util.Log
import com.example.appandres.data.network.endpoints.CartasEndPoint
import com.example.appandres.data.network.entity.allCartas.Item
import com.example.appandres.data.network.repository.RetrofitBase


class GetAllCartasUserCase {
    suspend operator fun invoke(): Result<List<Item>?> {
        var response = RetrofitBase.returnBaseRetrofitClash()
            .create(CartasEndPoint::class.java)
            .getAllCartas()
        Log.d("TAG", "a ver a ver")
        return if (response.isSuccessful){
            //el body nos devuelve la data
            Result.success(response.body()?.items)

        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }

    }

}