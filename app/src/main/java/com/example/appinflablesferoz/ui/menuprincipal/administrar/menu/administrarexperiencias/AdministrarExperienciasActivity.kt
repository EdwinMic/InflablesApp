package com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.administrarexperiencias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.NuestrasExperienciasAdapter
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.NuestrasExperienciasViewModel
import com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias.addnuevasexperiencias.AddNuevasExperienciasActivity
import kotlinx.android.synthetic.main.activity_administrar_experiencias.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.toolbar

class AdministrarExperienciasActivity : AppCompatActivity() {

    private lateinit var adapter: AdministrarExperienciasAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(AdministrarExperienciasViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_experiencias)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Experiencias"
        }
        adapter = AdministrarExperienciasAdapter(this)


        rvExperienciasAdministrar.layoutManager = LinearLayoutManager(this)
        rvExperienciasAdministrar.adapter = adapter
        observerData()


    }

    override fun onStart() {
        super.onStart()
        observerData()
    }
    fun observerData() {
        viewModel.fetchDataAdministrarExperiencias().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
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