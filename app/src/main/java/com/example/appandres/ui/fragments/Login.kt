package com.example.appandres.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.appandres.R
import com.example.appandres.data.local.repository.DataBaseRepository
import com.example.appandres.databinding.FragmentLoginBinding
import com.example.appandres.ui.acitvities.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Login( conexion : DataBaseRepository) : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var conexion: DataBaseRepository = conexion
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        initListeners()
    }
    private fun initListeners() {
        binding.btnIngresar.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                conexion.getUserDao().getUserByName(binding.txtUsario.text.toString())
            }
        }

        binding.txtRegistrarse.setOnClickListener{
            (activity as MainActivity).replaceFragment(Register(conexion))
        }



    }


}