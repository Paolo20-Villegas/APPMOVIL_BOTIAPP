package com.gael.t1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Producto(val codigo: Int, val nombre: String, val descripcion: String, val precio: Float) : java.io.Serializable

class Listado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtención de los productos pasados desde InicioActivity
        val productos = intent.getSerializableExtra("productos") as? ArrayList<Producto>

        if (productos != null) {
            val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

            // Agregar cada producto al TableLayout
            for (producto in productos) {
                val tableRow = TableRow(this)

                val tvCodigo = TextView(this).apply { text = producto.codigo.toString() }
                val tvNombre = TextView(this).apply { text = producto.nombre }
                val tvDescripcion = TextView(this).apply { text = producto.descripcion }
                val tvPrecio = TextView(this).apply { text = producto.precio.toString() }

                tableRow.addView(tvCodigo)
                tableRow.addView(tvNombre)
                tableRow.addView(tvDescripcion)
                tableRow.addView(tvPrecio)

                tableLayout.addView(tableRow)
            }
        } else {
            Toast.makeText(this, "No hay productos disponibles para mostrar", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para regresar
    fun Regresar(view: View?) {
        val intent = Intent(this, InicioActivity::class.java)
        val options = ActivityOptionsCompat.makeCustomAnimation(
            this, R.anim.slide_in_left, R.anim.slide_out_right
        )
        startActivity(intent, options.toBundle())
        finish()
    }

    // Sobrescribir el método onBackPressed para agregar la animación
    override fun onBackPressed() {
        super.onBackPressed()
        val options = ActivityOptionsCompat.makeCustomAnimation(
            this, R.anim.slide_in_left, R.anim.slide_out_right
        )
        startActivity(intent, options.toBundle())
        finish()
    }
}