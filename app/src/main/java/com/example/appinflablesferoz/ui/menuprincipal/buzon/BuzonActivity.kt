package com.example.appinflablesferoz.ui.menuprincipal.buzon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.appinflablesferoz.R
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.*

class BuzonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buzon)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Quejas y Sugerencias"
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home ->

                super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }
}