package com.example.appandres.ui.entities.core

import android.app.Application
import androidx.room.Room
import com.example.appandres.data.local.repository.DataBaseRepository

class AppAndres : Application(){

    override fun onCreate() {
        super.onCreate()
        dbConnection = Room.databaseBuilder(applicationContext,
            DataBaseRepository::class.java,
            "Datos"
        ).build()

    }

    companion object{
        private var dbConnection : DataBaseRepository? = null
        fun getDBConnection():DataBaseRepository = dbConnection!!
    }
}