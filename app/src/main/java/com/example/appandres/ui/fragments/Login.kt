package com.example.appandres.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.appandres.R
import com.example.appandres.data.local.database.entity.UserDB
import com.example.appandres.data.local.repository.DataBaseRepository
import com.example.appandres.databinding.FragmentLoginBinding
import com.example.appandres.logic.userCase.login.LoginUserPassUserCase
import com.example.appandres.ui.acitvities.ActivityAllCartas
import com.example.appandres.ui.acitvities.MainActivity
import com.example.appandres.ui.core.AppAndres
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor


class Login( conexion : DataBaseRepository) : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricManager: BiometricManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.bind(
            inflater.inflate(
                R.layout.fragment_login,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        initListeners()
    }
    private fun initBiometric(){
        val checkBiometric = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.DEVICE_CREDENTIAL or BiometricManager.Authenticators.BIOMETRIC_STRONG
        )

        when(checkBiometric){
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {}
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {}
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG
                                or
                                BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    )
                }
                startActivity(enrollIntent)
            }else -> {
            Snackbar.make(
                binding.imgFinger, "Ocurrio un error inesperado en el sensor",
                Snackbar.LENGTH_LONG
            ).show()
        }

        }
    }

    private fun initVariables() {
        biometricManager = BiometricManager.from(requireActivity())
        executor = ContextCompat.getMainExecutor(requireActivity())
        promptInfo =BiometricPrompt.PromptInfo.Builder()
            .setTitle("App Andres")
            .setSubtitle("Ingrese su huella digital")
            .setNegativeButtonText("Cancelar")
            .build()

        biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    val x = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(x)
                }
            }
        )
    }

    private fun initListeners() {
        binding.btnIngresar.setOnClickListener{
            val loginUserCase = LoginUserPassUserCase(
                AppAndres.getDBConnection()
            )

            lifecycleScope.launch(Dispatchers.Main) {
                binding.lytLoading.root.visibility = View.VISIBLE
                val result = withContext(Dispatchers.IO){
                    loginUserCase(
                        binding.txtUsario.text.toString(),
                        binding.txtPass.text.toString()
                    )
                }
                result.onSuccess { user ->
                    val intentToAllCartas = Intent(
                        requireActivity(),
                        ActivityAllCartas::class.java
                    )
                    startActivity(intentToAllCartas)
                    binding.lytLoading.root.visibility = View.GONE
                }
                result.onFailure {
                    Toast.makeText(
                        requireContext(),
                        it.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            binding.lytLoading.root.visibility = View.GONE
        }



        binding.txtRegistrarse.setOnClickListener{
            (activity as MainActivity).replaceFragment(Register())
        }

        binding.imgFinger.setOnClickListener{
            initBiometric()
        }



    }


}