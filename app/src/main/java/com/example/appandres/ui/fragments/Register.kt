package com.example.appandres.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.appandres.R
import com.example.appandres.data.local.database.entity.UserDB
import com.example.appandres.data.local.repository.DataBaseRepository
import com.example.appandres.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Register(conexion : DataBaseRepository) : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private var conexion: DataBaseRepository = conexion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.bind(
            inflater.inflate(
                R.layout.fragment_register,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener{
            val user = binding.txtNewUser.text.toString()
            val pass = binding.txtNewPass.text.toString()
            val nom = binding.txtName.text.toString()
            val email = binding.txtEmail.text.toString()
            val userDB = UserDB(name=user, password = pass, nombre_completo = nom, email = email)

            lifecycleScope.launch(Dispatchers.IO) {
                conexion.getUserDao().saveUser(listOf(userDB))
            }
        }
    }


}