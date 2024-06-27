package com.example.appandres.logic.userCase.login

import com.example.appandres.data.local.database.entity.UserDB
import com.example.appandres.data.local.repository.DataBaseRepository

class LoginUserPassUserCase(private val conexion: DataBaseRepository) {
    suspend operator fun invoke(
        user: String,
        password: String
    ): Result<UserDB> {
        return try {
            val us = conexion.getUserDao().getUserByNameAndPass(user, password)
            Result.success(us)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}