package com.example.appandres.ui.acitvities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appandres.data.local.repository.DataBaseRepository
import com.example.appandres.databinding.ActivityMainBinding
import com.example.appandres.ui.core.AppAndres
import com.example.appandres.ui.fragments.Login

class MainActivity : AppCompatActivity() {

    //Recordar para obtener el binding se debe activiar en el gradle dentro de Android se crea el metodo buildFeatures y dentro se coloca viewBinding = true
    private  lateinit var binding: ActivityMainBinding
    //
    private lateinit var conexion: DataBaseRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializamos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // es un metodo que se usa para definir que vista (View) sera la interfaz de usuario de la actividad
        //root es una propiedad de la isntancia que representa la vista raiz del archivo de diseño actvity_main
        setContentView(binding.root)
        conexion = AppAndres.getDBConnection()

        // Inicialmente muestra el fragmento de Login
        replaceFragment(Login(conexion))
    }

    fun replaceFragment(fragment: Fragment){
        val x = supportFragmentManager.beginTransaction()
        x.replace(binding.lytFragment.id,fragment)
        x.commit()
    }

}