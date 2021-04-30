package com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.miperfil.addnuevasexperiencias.AddNuevasExperienciasActivity
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.toolbar

class NuestrasExperienciasActivity : AppCompatActivity() {

    private lateinit var adapter: NuestrasExperienciasAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(NuestrasExperienciasViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuestras_experiencias)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Experiencias"
        }
        adapter = NuestrasExperienciasAdapter(this)


        rvExperiencias.layoutManager = LinearLayoutManager(this)
        rvExperiencias.adapter = adapter
        observerData()

        fabAddExperiencia.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddNuevasExperienciasActivity::class.java)
            startActivity(intent)
        })

    }

    override fun onStart() {
        super.onStart()
        observerData()
    }
    fun observerData() {
        viewModel.fetchDataExperiencias().observe(this, Observer {
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