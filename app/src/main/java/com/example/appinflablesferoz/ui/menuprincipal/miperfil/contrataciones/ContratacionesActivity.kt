package com.example.appinflablesferoz.ui.menuprincipal.miperfil.contrataciones

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appinflablesferoz.BuscarDireccionMapsActivity
import com.example.appinflablesferoz.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_contrataciones.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.toolbar




class ContratacionesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrataciones)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Contratar"
        }

        mtbtnAddContratoBuscarDir.setOnClickListener(View.OnClickListener {
            if(etAddContratoDireccion.length() > 0){
                val intent = Intent(this, BuscarDireccionMapsActivity::class.java).apply {
                    putExtra("EXTRA_DIRECCION",etAddContratoDireccion.text.toString())
                }
                startActivity(intent)
            }else{
                Snackbar.make(containerAddContrato,"Agrega una direcciom", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", View.OnClickListener {

                    }).show()
            }


        })


    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home ->

                super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }

}