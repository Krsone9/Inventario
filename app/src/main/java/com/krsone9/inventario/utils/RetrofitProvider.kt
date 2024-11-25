package com.krsone9.inventario.utils

import com.krsone9.inventario.services.CategoriasService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
            fun getRetrofit() : CategoriasService {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/products/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return retrofit.create(CategoriasService::class.java)
            }
        }
}