package com.example.appandres.data.local.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appandres.data.local.database.dao.UserDAO
import com.example.appandres.data.local.database.entity.UserDB

@Database (
    entities = [UserDB::class],
    version = 1
)
abstract class DataBaseRepository:RoomDatabase() {
    abstract fun getUserDao():UserDAO
}