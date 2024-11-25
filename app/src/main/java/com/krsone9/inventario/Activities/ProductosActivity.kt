package com.krsone9.inventario.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.krsone9.inventario.R


class ProductosActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CATEGORIA_ID = "EXTRA_CATEGORIA_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
    }
}