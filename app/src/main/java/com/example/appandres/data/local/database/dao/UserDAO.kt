package com.example.appandres.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appandres.data.local.database.entity.UserDB

@Dao
interface UserDAO {
    @Query("select*from users")
    fun getAllUsers() : List<UserDB>

    @Query("select*from users where id_user =:id")
    fun getUserById(id:Int):UserDB

    @Query("select*from users where name_user =:name")
    fun getUserByName(name:String):UserDB

    @Insert
    fun saveUser(user: List<UserDB>)

    @Delete
    fun saveUser(user: UserDB)
}