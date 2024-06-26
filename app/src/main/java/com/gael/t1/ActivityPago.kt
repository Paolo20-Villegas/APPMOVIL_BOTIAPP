package com.gael.t1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.enableEdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton_PagoLinea: RadioButton
    private lateinit var radioButton_PagoEfectivo: RadioButton
    private lateinit var btn_Pagar: Button
    private lateinit var txtViewPagoMed: TextView
    private lateinit var txtViewPagoDelivery: TextView
    private lateinit var txtViewPagoTotal: TextView

    // Valores de ejemplo para los montos
    private val medicamentAmount = 50.0
    private val deliveryAmount = 10.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioGroup = findViewById(R.id.radioGroup)
        radioButton_PagoLinea = findViewById(R.id.radioButton_PagoLinea)
        radioButton_PagoEfectivo = findViewById(R.id.radioButton_PagoEfectivo)
        btn_Pagar = findViewById(R.id.btn_Pagar)
        txtViewPagoMed = findViewById(R.id.txtView_PagoMed)
        txtViewPagoDelivery = findViewById(R.id.txtView_PagoDelivery)
        txtViewPagoTotal = findViewById(R.id.txtView_PagoTotal)

        // Mostrar los montos iniciales
        mostrarMontos()

        btn_Pagar.setOnClickListener {
            val selectedOption = radioGroup.checkedRadioButtonId
            when (selectedOption) {
                R.id.radioButton_PagoLinea -> {
                    Toast.makeText(this, "Su pago en linea ha sido exitoso", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.radioButton_PagoEfectivo -> {
                    Toast.makeText(this, "Su compra ha sido realizada", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(
                        this,
                        "Por favor, seleccione una opción de pago",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
    private fun mostrarMontos() {
        txtViewPagoMed.text = "S/${medicamentAmount}"
        txtViewPagoDelivery.text = "S/${deliveryAmount}"
        val totalAmount = medicamentAmount + deliveryAmount
        txtViewPagoTotal.text = "S/${totalAmount}"
    }
}