package com.krsone9.inventario.data

import com.google.gson.annotations.SerializedName


data class CategoriasListResponse (
    @SerializedName("results") val results: String){}

data class ProductosListResponse (
    @SerializedName("products") val products: List<ProductosClass>){}

data class ProductosClass (
    @SerializedName("category") val category: String,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val image: String){}












