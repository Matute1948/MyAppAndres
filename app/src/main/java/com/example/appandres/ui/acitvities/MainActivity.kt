package com.example.appandres.ui.acitvities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appandres.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Recordar para obtener el binding se debe activiar en el gradle dentro de Android se crea el metodo buildFeatures y dentro se coloca viewBinding = true
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializamos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // es un metodo que se usa para definir que vista (View) sera la interfaz de usuario de la actividad
        //root es una propiedad de la isntancia que representa la vista raiz del archivo de diseño actvity_main
        setContentView(binding.root)

    }

}