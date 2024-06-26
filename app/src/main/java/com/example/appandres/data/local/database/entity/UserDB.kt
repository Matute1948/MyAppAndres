package com.example.appandres.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDB (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    val id:Int = 0,
    @ColumnInfo(name = "name_user")
    val name: String,
    @ColumnInfo(name = "password_user")
    val password: String,
    @ColumnInfo(name = "nombre_completo")
    val nombre_completo: String,
    @ColumnInfo(name = "email")
    val email: String
)