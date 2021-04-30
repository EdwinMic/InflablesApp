package com.example.appinflablesferoz.ui.menuprincipal.inflables

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.ui.menuprincipal.inflables.resumeninflable.ResumenInflableActivity
import kotlinx.android.synthetic.main.activity_inflables.*

class InflablesActivity : AppCompatActivity() {

    private lateinit var adapter: InflablesAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(InflablesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflables)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Inflables"
        }


        /*adapter = InflablesAdapter(this)
        rvInflables.layoutManager = LinearLayoutManager(this)
        rvInflables.adapter = adapter*/


        observerData()
    }

    fun observerData() {


       /* viewModel.fetchUserData().observe(this, Observer {
            adapter.inflableList(it)
            adapter.notifyDataSetChanged()

        })
*/
        rvInflables.layoutManager = LinearLayoutManager(this)
        rvInflables.adapter = InflablesAdapter{
            val intent = Intent(this, ResumenInflableActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        viewModel.getDatosInflables().observe(this, Observer {
            (rvInflables.adapter as InflablesAdapter).setData(it)
            (rvInflables.adapter as InflablesAdapter).notifyDataSetChanged()
        })

    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when(item){
                case android.R.id.home:
                //finish();
                //ejecuta el metodo finish despues de la transicion
                super.onBackPressed();
                break;
            }
        return super.onOptionsItemSelected(item)
    }
*/


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home ->

                super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }
}