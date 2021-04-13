package com.example.appinflablesferoz.ui.menuprincipal.inflables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import kotlinx.android.synthetic.main.activity_inflables.*

class InflablesActivity : AppCompatActivity() {

    private lateinit var adapter: InflablesAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(InflablesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflables)

        adapter = InflablesAdapter(this)


        rvInflables.layoutManager = LinearLayoutManager(this)
        rvInflables.adapter = adapter
        observerData()
    }
    fun observerData() {
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })

    }
}