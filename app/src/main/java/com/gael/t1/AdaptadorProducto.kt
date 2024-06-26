package com.gael.t1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorProducto (
    var context: Context,
    var tvCantProductos: TextView,
    var btnVerCarro: Button,
    var listaProductos: ArrayList<Producto2>,
    var carroCompras: ArrayList<Producto2>
): RecyclerView.Adapter<AdaptadorProducto.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNomProducto2 = itemView.findViewById(R.id.tvNomProducto) as TextView
        val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
        val cbCarro = itemView.findViewById(R.id.cbCarro) as CheckBox
        val tvPrecio = itemView.findViewById(R.id.tvPrecio) as TextView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_productos, parent,false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto2 = listaProductos[position]

        holder.tvNomProducto2.text = producto2.nomProducto
        holder.tvDescripcion.text = producto2.descripcion
        holder.tvPrecio.text = producto2.precio.toString()

        holder.cbCarro.setOnCheckedChangeListener { compoundButton, b ->
            if (holder.cbCarro.isChecked) {
                tvCantProductos.text = "${Integer.parseInt(tvCantProductos.text.toString().trim()) + 1}"
                carroCompras.add(listaProductos[position])
            }else {
                tvCantProductos.text = "${Integer.parseInt(tvCantProductos.text.toString().trim()) - 1}"
                carroCompras.remove(listaProductos[position])
            }
        }
        btnVerCarro.setOnClickListener{
            val intent = Intent(context, CarroComprasActivity::class.java)
            intent.putExtra("carro_Compras", carroCompras)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}