package com.krsone9.inventario.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.krsone9.inventario.Adapters.ProductosAdapter
import com.krsone9.inventario.data.ProductosClass
import com.krsone9.inventario.databinding.ActivityProductosBinding
import com.krsone9.inventario.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductosActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductosBinding
    lateinit var adapter: ProductosAdapter
    var productosList: List<ProductosClass> = emptyList()

    companion object {
        const val EXTRA_CATEGORIA_ID = "EXTRA_CATEGORIA_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = ProductosAdapter(productosList, { position ->
            val id_producto = productosList[position].id
            navigateToDetailProducto(id_producto)
        })


        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)

        LoadProductosAPI()
    }

    // ------------
    private fun navigateToDetailProducto(id_Product: Int) {
        val intent = Intent(this, ProductoDetalleActivity::class.java)
        intent.putExtra(ProductoDetalleActivity.EXTRA_PRODUCTO_ID, id_Product)
        startActivity(intent)
    }

    // Cargamos los datos de las categorias de la API --------------------------------------------------
    private fun LoadProductosAPI() {
        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {                   // hay que ejecutar la consulta en un hilo secundario
            try {
                val result =
                    service.ProductosListResponse(intent.getStringExtra(EXTRA_CATEGORIA_ID)!!)

                CoroutineScope(Dispatchers.Main).launch {         // volvemos al hilo principal para mostrar resultados
                    if (result.products.isEmpty()) {
                        // Toast.makeText(this, "No se han encontrado resultados", Toast.LENGTH_SHORT).show()
                    } else {
                        productosList = result.products
                        adapter.updateItems(productosList)
                    }
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}