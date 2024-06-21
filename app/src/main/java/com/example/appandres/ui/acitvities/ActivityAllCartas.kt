package com.example.appandres.ui.acitvities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appandres.databinding.ActivityAllCartasBinding
import com.example.appandres.logic.userCase.GetAllCartasUserCase
import com.example.appandres.ui.adapters.AllCartasAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityAllCartas : AppCompatActivity() {

    lateinit var binding : ActivityAllCartasBinding

    private  lateinit var allCartasAdapter : AllCartasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCartasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initData()
    }

    private fun initVariables() {
        allCartasAdapter = AllCartasAdapter()

        binding.rvAllCartas.adapter = allCartasAdapter


        binding.rvAllCartas.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }

    private fun initData() {
        binding.pgbarLoadData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val result = GetAllCartasUserCase().invoke()

            withContext(Dispatchers.Main){
                binding.pgbarLoadData.visibility = View.INVISIBLE
                result.onSuccess {

                    allCartasAdapter.listCartas = it!!
                    allCartasAdapter.notifyDataSetChanged()
                }
                result.onFailure {
                    Snackbar.make(binding.refreshRV, it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}