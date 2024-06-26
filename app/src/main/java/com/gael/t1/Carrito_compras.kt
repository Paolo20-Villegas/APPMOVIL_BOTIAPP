package com.gael.t1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gael.t1.databinding.CarritoComprasBinding

class Carrito_compras : AppCompatActivity(){

    private lateinit var binding: CarritoComprasBinding
    private lateinit var adapter: AdaptadorProducto

    var listaProductos = ArrayList<Producto2>()
    var carroCompras = ArrayList<Producto2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CarritoComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        agregarProductos()
    }
    fun RecyclerView(){
        fun setupRecyclerView() {
            binding.rvListaProductos.layoutManager = LinearLayoutManager(this)
            adapter = AdaptadorProducto(this, binding.tvCantProductos, binding.btnVerCarro, listaProductos, carroCompras)
            binding.rvListaProductos.adapter = adapter
        }
    }
    fun agregarProductos(){
        listaProductos.add(Producto2("1", "Producto 1", "Descripcion del Producto 1", 50.0))
        listaProductos.add(Producto2("2", "Producto 2", "Descripcion del Producto 2", 80.0))
        listaProductos.add(Producto2("3", "Producto 3", "Descripcion del Producto 3", 40.0))
        listaProductos.add(Producto2("4", "Producto 4", "Descripcion del Producto 4", 20.0))
        listaProductos.add(Producto2("5", "Producto 5", "Descripcion del Producto 5", 56.0))
    }
}