package com.alvaromena.practica_2

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {
    private var cal = Calendar.getInstance()
    var fecha=""//si se declara usando private lateinit se genera un problema y se cierra la app cuando el unico dato que falta es la fecha
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "dd/MM/yyyy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tv_fecha_de_nacimiento.text = fecha
            }
        }

        ib_calendario.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        bt_guardar.setOnClickListener {

            val nombre = et_nombre.text.toString()
            val email = et_correo.text.toString()
            val telefono = et_telefono.text.toString()
            val clave = et_clave.text.toString()
            val rclave = et_repetir_clave.text.toString()
            val genero = if (rb_masculino.isChecked) "Masculino" else "Femenino"
            val lugar_dnacimiento = sp_lugar_naciminto.selectedItem.toString()

            var hobbies = ""
            if (cb_Deportes.isChecked) hobbies = "$hobbies Deportes"
            if (cb_juegos.isChecked) hobbies = "$hobbies Video juegos"
            if (cb_lectura.isChecked) hobbies = "$hobbies Lectura"
            if (cb_musica.isChecked) hobbies = "$hobbies Musica"
            if (clave == rclave && !(clave.isEmpty()) ) {
                if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || fecha.isEmpty() || hobbies.isEmpty() || lugar_dnacimiento.isEmpty()) {
                    tv_resultados.text = "Por favor ingrese todos los datos"
                } else {
                    onBackPressed()//limpio la pila de actividades
                    tv_resultados.text = "Nombre: $nombre\n" +
                            "Correo electronico: $email\n" +
                            "Telefono: $telefono\n" +
                            "Genero: $genero\n" +
                            "Fecha de nacimiento: $fecha\n" +
                            "Lugar de nacimiento: $lugar_dnacimiento\n" +
                            "Pasatiempos: $hobbies\n"

                    val intent: Intent = Intent(this, LoginActivity::class.java )
                    intent.putExtra("correo",email)
                    intent.putExtra("clave",clave)
                    startActivity(intent)

                }
            } else
                tv_resultados.text = "Las contraseñas no coinciden o no a introducido una contraseña"
        }


    }
}