package com.krsone9.inventario.services

import com.krsone9.inventario.data.CategoriasListResponse
import com.krsone9.inventario.data.ProductosClass
import com.krsone9.inventario.data.ProductosListResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface CategoriasService {

// LLamada para obtener categorias
        @GET("category-list")
        suspend fun CategoriasListResponse(): List<String>

// LLamada para obtener productos
        @GET("category/{categoria}")
        suspend fun ProductosListResponse(@Path("categoria") categoria: String): ProductosListResponse
}


