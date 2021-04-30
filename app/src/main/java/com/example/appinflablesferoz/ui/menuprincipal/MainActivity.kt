package com.example.appinflablesferoz.ui.menuprincipal

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.MenuAdministracionActivity
import com.example.appinflablesferoz.ui.menuprincipal.contactos.ContactosActivity
import com.example.appinflablesferoz.ui.menuprincipal.inflables.InflablesActivity
import com.example.appinflablesferoz.ui.menuprincipal.inflables.InflablesAdapter
import com.example.appinflablesferoz.ui.menuprincipal.inflables.resumeninflable.ResumenInflableActivity
import com.example.appinflablesferoz.ui.menuprincipal.miperfil.MiPerfilActivity
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.NuestrasExperienciasActivity
import com.example.appinflablesferoz.ui.menuprincipal.ubicanos.UbicanosActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_inflables.*
import kotlinx.android.synthetic.main.activity_main.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    //private lateinit var adapter: MainActivityAdapter

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

        /*opcionContrataciones.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, ContratacionesActivity::class.java)
            startActivity(intento)
        })*/

        opcionUbicanos.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UbicanosActivity::class.java)
            startActivity(intent)
        })

        opcionExperiencias.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, NuestrasExperienciasActivity::class.java)
            startActivity(intento)
        })

        /*opcionBuzon.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, BuzonActivity::class.java)
            startActivity(intento)
        })*/

        opcionMiPerfil.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, MiPerfilActivity::class.java)
            startActivity(intento)
        })

        Log.e("","isNetDisponible:::" + isNetDisponible())
        //initUI()
        observerData()
    }

    fun observerData() {

        //adapter = MainActivityAdapter(this)
        rvScrollImage.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        //rvScrollImage.adapter = adapter
        rvScrollImage.adapter = MainActivityAdapter{
            val intent = Intent(this, ResumenInflableActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        /*viewModel.fetchDataScrollImage().observe(this, Observer {
            (rvScrollImage.adapter as MainActivityAdapter).setListData(it)
        })*/
        viewModel.fetchDataScrollImage().observe(this, Observer {
            (rvScrollImage.adapter as MainActivityAdapter).setListData(it)
            (rvScrollImage.adapter as MainActivityAdapter).notifyDataSetChanged()
            //val carousel: ImageCarousel = findViewById(R.id.carouselInicial)
            //carousel.addData(it)
            initUI()
        })



        if (isNetDisponible()){

        }else{
            Toast.makeText(this,"Conectate a Internet", Toast.LENGTH_LONG).show()
        }


    }
    fun initUI(){



        /*if (isNetDisponible()){*/
            //imgFotoGifMenuInflables.visibility = View.INVISIBLE
        viewModel.urlImagenTituloInflable.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerInside()
                //.placeholder(R.drawable.loading)
                .into(imgFotoMenuInflables)
            //imgFotoGifMenuInflables.visibility = View.INVISIBLE

        })

        viewModel.urlImagenTituloContactos.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                //.placeholder(R.drawable.loading)
                .into(imgFotoMenuContactos)
        })
       /* viewModel.urlImagenTituloContrataciones.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgFotoMenuContrataciones)

        })*/
        viewModel.urlImagenTituloUbicanos.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgFotoMenuUbicanos)
        })


        viewModel.urlImagenTituloExperiencias.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgFotoMenuExperiencias)

        })

        /*viewModel.urlImagenTituloBuzon.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgFotoMenuBuzon)

        })*/

        viewModel.urlImagenTituloAdministrar.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgFotoMenuAdministrar)

        })

        viewModel.urlImagenTituloMiPerfil.observe(this, Observer {
            Picasso.get()
                .load(it)
                .resize(88, 88)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgFotoMenuMiPerfil)

        })

       /* }else{
            //imgFotoGifMenuInflables.visibility = View.VISIBLE
            Toast.makeText(this,"Conectate a Internet", Toast.LENGTH_LONG).show()
        }*/

    }

    private fun isNetDisponible(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val actNetInfo = connectivityManager.activeNetworkInfo
        return actNetInfo != null && actNetInfo.isConnected
    }




    override fun onStart() {
        super.onStart()
        observerData()
        //itUI()
    }

}