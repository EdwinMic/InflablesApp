package com.example.appinflablesferoz.ui.menuprincipal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.MenuAdministracionActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModelMainActivity: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelMainActivity = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //val imgFotoMenuInflables: CircleImageView = findViewById(R.id.imgFotoMenuInflables)


        val btnAdministrar=findViewById<ConstraintLayout>(R.id.opcionAdministrar)
        btnAdministrar.setOnClickListener {
            val intento = Intent(this, MenuAdministracionActivity::class.java)
            startActivity(intento)
        }


        initUI()
    }


    fun initUI(){
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

        viewModelMainActivity.urlImagenTituloUbicanos.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuUbicanos)
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

        viewModelMainActivity.urlImagenTituloAdministrar.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .into(imgFotoMenuAdministrar)

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
}