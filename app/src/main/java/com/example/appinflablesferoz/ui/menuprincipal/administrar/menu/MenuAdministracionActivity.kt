package com.example.appinflablesferoz.ui.menuprincipal.administrar.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.MainActivityViewModel
import com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.addinflables.AddInflablesActivity
import com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.administrarexperiencias.AdministrarExperienciasActivity
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.addnuevasexperiencias.AddNuevasExperienciasActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.imgFotoMenuBuzon
import kotlinx.android.synthetic.main.activity_main.imgFotoMenuContactos
import kotlinx.android.synthetic.main.activity_main.imgFotoMenuExperiencias
import kotlinx.android.synthetic.main.activity_main.imgFotoMenuInflables
import kotlinx.android.synthetic.main.activity_menu_administracion.*

class MenuAdministracionActivity : AppCompatActivity() {

    lateinit var viewModelMainActivity: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_administracion)


        viewModelMainActivity = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        val btnAddInflables = findViewById<ConstraintLayout>(R.id.opcionAddInflables)
        btnAddInflables.setOnClickListener {
            val intent = Intent(this, AddInflablesActivity::class.java)
            startActivity(intent)
        }

        opcionAddExperiencias.setOnClickListener {
            val intent = Intent(this, AdministrarExperienciasActivity::class.java)
            startActivity(intent)
        }

        initUI()
    }


    fun initUI() {
        viewModelMainActivity.urlImagenTituloInflable.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuInflables)

        })


        viewModelMainActivity.urlImagenTituloContactos.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuContactos)
        })


        viewModelMainActivity.urlImagenTituloExperiencias.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuExperiencias)

        })

        viewModelMainActivity.urlImagenTituloBuzon.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuBuzon)

        })



    }
}