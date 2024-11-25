package com.krsone9.inventario.data

import com.google.gson.annotations.SerializedName


data class CategoriasListResponse (
    @SerializedName("results") val results: String){}

data class ProductosListResponse (
    @SerializedName("results") val results: String){}

data class ProductoClass (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String){}



