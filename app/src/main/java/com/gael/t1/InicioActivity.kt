package com.gael.t1

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat

class InicioActivity : AppCompatActivity() {

    // Variables globales
    private var etCodigo: EditText? = null
    private var etNombre: EditText? = null
    private var etDescripcion: EditText? = null
    private var etPrecio: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        // Inicializar las variables con los elementos de la interfaz
        etCodigo = findViewById(R.id.txt_codigo2)
        etNombre = findViewById(R.id.txt_nombre2)
        etDescripcion = findViewById(R.id.txt_descripcion2)
        etPrecio = findViewById(R.id.txt_precio2)

        // Obtener el nombre de usuario desde el Intent
        val username = intent.getStringExtra("username")

        // Mostrar el mensaje de bienvenida
        if (username != null) {
            Toast.makeText(this, "Bienvenido, $username", Toast.LENGTH_LONG).show()
        }
    }

    // Método para registrar productos
    fun Registrar(view: View) {
        val admin = AdminSQLiteOpenHelperx(this, "administracion", null, 3)
        val baseDeDatos: SQLiteDatabase = admin.writableDatabase
        val nombre = etNombre?.text.toString()
        val descripcion = etDescripcion?.text.toString()
        val precio = etPrecio?.text.toString()
        if (nombre.isNotEmpty() && descripcion.isNotEmpty() && precio.isNotEmpty()) {
            val registro = ContentValues()
            registro.put("nombre", nombre)
            registro.put("descripcion", descripcion)
            registro.put("precio", precio)
            val cantReg = baseDeDatos.insert("productos", null, registro)

            baseDeDatos.close()
            etNombre?.setText("")
            etDescripcion?.setText("")
            etPrecio?.setText("")
            if (cantReg == -1L) {
                Toast.makeText(this, "Error o Registro ya existente", Toast.LENGTH_SHORT).show()
            } else {
                etCodigo?.setText("")
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun Editar(view: View?) {
        val admin = AdminSQLiteOpenHelperx(this, "administracion", null, 3)
        val baseDeDatos = admin.writableDatabase
        val codigo = etCodigo!!.text.toString()
        val nombre = etNombre!!.text.toString()
        val descripcion = etDescripcion!!.text.toString()
        val precio = etPrecio!!.text.toString()

        if (codigo.isNotEmpty() && nombre.isNotEmpty() && descripcion.isNotEmpty() && precio.isNotEmpty()) {
            val valores = ContentValues().apply {
                put("nombre", nombre)
                put("descripcion", descripcion)
                put("precio", precio)
            }
            val cantidad = baseDeDatos.update("productos", valores, "codigo = ?", arrayOf(codigo))
            baseDeDatos.close()

            if (cantidad > 0) {
                Toast.makeText(this, "Producto modificado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun Buscar(view: View?) {
        val admin = AdminSQLiteOpenHelperx(this, "administracion", null, 3)
        val baseDeDatos = admin.writableDatabase
        val codigo = etCodigo!!.text.toString()
        if (codigo.isNotEmpty()) {
            val fila = baseDeDatos.rawQuery("SELECT nombre, descripcion, precio FROM productos WHERE codigo = ?", arrayOf(codigo))
            if (fila.moveToFirst()) {
                etNombre!!.setText(fila.getString(0))
                etDescripcion!!.setText(fila.getString(1))
                etPrecio!!.setText(fila.getString(2))
                baseDeDatos.close()
            } else {
                etNombre!!.setText("")
                etDescripcion!!.setText("")
                etPrecio!!.setText("")
                Toast.makeText(this, "No existe el producto", Toast.LENGTH_SHORT).show()
                baseDeDatos.close()
            }
        } else {
            Toast.makeText(this, "Debes introducir el código del producto", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para eliminar un producto
    fun Eliminar(view: View?) {
        val admin = AdminSQLiteOpenHelperx(this, "administracion", null, 3)
        val baseDeDatos = admin.writableDatabase
        val codigo = etCodigo!!.text.toString()
        if (codigo.isNotEmpty()) {
            val cantidad = baseDeDatos.delete("productos", "codigo = ?", arrayOf(codigo))
            baseDeDatos.close()
            etNombre!!.setText("")
            etDescripcion!!.setText("")
            etPrecio!!.setText("")
            if (cantidad == 1) {
                etCodigo!!.setText("")
                Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Debes de introducir el código del producto", Toast.LENGTH_SHORT).show()
        }
    }

    fun Mostrar(view: View?) {
        val admin = AdminSQLiteOpenHelperx(this, "administracion", null, 3)
        val baseDeDatos = admin.writableDatabase
        val cursor = baseDeDatos.rawQuery("SELECT * FROM productos", null)

        val productos = ArrayList<Producto>()

        if (cursor.moveToFirst()) {
            do {
                val codigo = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val descripcion = cursor.getString(2)
                val precio = cursor.getFloat(3)
                productos.add(Producto(codigo, nombre, descripcion, precio))
            } while (cursor.moveToNext())
        }

        cursor.close()
        baseDeDatos.close()

        val intent = Intent(this, Listado::class.java)
        intent.putExtra("productos", productos)
        val options = ActivityOptionsCompat.makeCustomAnimation(
            this, R.anim.slide_in_right, R.anim.slide_out_left
        )
        startActivity(intent, options.toBundle())
    }
    fun Escape(view: View?) {
        val intent = Intent(this, ActivityLogin::class.java)
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
