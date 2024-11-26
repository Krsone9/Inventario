package com.krsone9.inventario.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.krsone9.inventario.Adapters.CategoriasAdapter
import com.krsone9.inventario.R
import com.krsone9.inventario.databinding.ActivityMainBinding
import com.krsone9.inventario.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CategoriasAdapter
    var categoriasList: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CategoriasAdapter(categoriasList, { position -> val categoria = categoriasList[position]
                                                      navigateToProductos(categoria)
        })

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)

        LoadCategoriasAPI()
    }

    // Vamos al activity de Productos, pasando el parametro de busqueda de los productos de la categoria
    private fun navigateToProductos(categoria: String) {
        val intent = Intent(this, ProductosActivity::class.java)
        intent.putExtra(ProductosActivity.EXTRA_CATEGORIA_ID, categoria)
        startActivity(intent)
    }

    // Cargamos los datos de las categorias de la API --------------------------------------------------
    private fun LoadCategoriasAPI() {
        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {                   // hay que ejecutar la consulta en un hilo secundario
            try {
                val result = service.CategoriasListResponse()

                CoroutineScope(Dispatchers.Main).launch {         // volvemos al hilo principal para mostrar resultados
                    if (result.isEmpty()) {
                        // Toast.makeText(this, "No se han encontrado resultados", Toast.LENGTH_SHORT).show()
                    } else {
                        categoriasList = result
                        adapter.updateItems(categoriasList)
                    }
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}