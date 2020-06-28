package com.alvaromena.practica_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var DatosRecibidos = intent.extras
        var correo = DatosRecibidos?.getString("correo")
        tv_main.text=correo

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //if(item.itemId==R.id.cerrar_sesion){
        onBackPressed()//limpio la pila de actividades para no regresar
        val intent: Intent = Intent(this, LoginActivity::class.java )
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}