package com.krsone9.inventario.services

import com.krsone9.inventario.data.CategoriasListResponse
import com.krsone9.inventario.data.ProductosListResponse
import retrofit2.http.GET

interface CategoriasService {

// LLamada para obtener categorias
        @GET("category-list")
        suspend fun CategoriasListResponse(): List<String>

// LLamada para obtener productos
        @GET("product-list")
        suspend fun ProductosListResponse(): List<String>
}


