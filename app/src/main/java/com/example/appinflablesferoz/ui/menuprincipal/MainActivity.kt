package com.example.appinflablesferoz.ui.menuprincipal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.MenuAdministracionActivity
import com.example.appinflablesferoz.ui.menuprincipal.buzon.BuzonActivity
import com.example.appinflablesferoz.ui.menuprincipal.contactos.ContactosActivity
import com.example.appinflablesferoz.ui.menuprincipal.contrataciones.ContratacionesActivity
import com.example.appinflablesferoz.ui.menuprincipal.inflables.InflablesActivity
import com.example.appinflablesferoz.ui.menuprincipal.miperfil.MiPerfilActivity
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.NuestrasExperienciasActivity
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.NuestrasExperienciasAdapter
import com.example.appinflablesferoz.ui.menuprincipal.ubicanos.UbicanosActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //val imgFotoMenuInflables: CircleImageView = findViewById(R.id.imgFotoMenuInflables)


        val btnAdministrar=findViewById<ConstraintLayout>(R.id.opcionAdministrar)
        btnAdministrar.setOnClickListener {
            val intento = Intent(this, MenuAdministracionActivity::class.java)
            startActivity(intento)
        }

        opcionInflables.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, InflablesActivity::class.java)
            startActivity(intento)
        })

        opcionContactos.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, ContactosActivity::class.java)
            startActivity(intento)
        })

        opcionContrataciones.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, ContratacionesActivity::class.java)
            startActivity(intento)
        })

        opcionUbicanos.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UbicanosActivity::class.java)
            startActivity(intent)
        })
        opcionExperiencias.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, NuestrasExperienciasActivity::class.java)
            startActivity(intento)
        })
        opcionBuzon.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, BuzonActivity::class.java)
            startActivity(intento)
        })

        opcionMiPerfil.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, MiPerfilActivity::class.java)
            startActivity(intento)
        })


        initUI()
    }


    fun initUI(){

        adapter = MainActivityAdapter(this)


        rvScrollImage.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        rvScrollImage.adapter = adapter
        observerData()

        viewModel.urlImagenTituloInflable.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuInflables)

        })


        viewModel.urlImagenTituloContactos.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuContactos)
        })
        viewModel.urlImagenTituloContrataciones.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuContrataciones)

        })
        viewModel.urlImagenTituloUbicanos.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuUbicanos)
        })


        viewModel.urlImagenTituloExperiencias.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuExperiencias)

        })

        viewModel.urlImagenTituloBuzon.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuBuzon)

        })

        viewModel.urlImagenTituloAdministrar.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuAdministrar)

        })

        viewModel.urlImagenTituloMiPerfil.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuMiPerfil)

        })

/*
        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            nameTextView.text = pokemon.sprites.other.oficialArtwork?.frontDefault
            heightText.text = "Altura: ${pokemon.height/10.0}m"
            weightText.text = "Peso: ${pokemon.weight/10.0}"

            //Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
            Glide.with(this).load(pokemon.sprites.other.oficialArtwork?.frontDefault)
                .into(imgFotoMenuInflables)

        })*/


    }

    fun observerData() {
        viewModel.fetchDataScrollImage().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })

    }

    override fun onStart() {
        super.onStart()
        observerData()
    }
}