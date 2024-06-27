package com.example.appandres.ui.acitvities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appandres.R
import com.example.appandres.databinding.ActivityBiometricBinding

class BiometricActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBiometricBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()

        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.sleep(1000)
        splash.setKeepOnScreenCondition { false }

    }
}