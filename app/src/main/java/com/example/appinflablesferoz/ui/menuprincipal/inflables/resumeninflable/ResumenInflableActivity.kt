package com.example.appinflablesferoz.ui.menuprincipal.inflables.resumeninflable

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Inflables
import com.example.appinflablesferoz.ui.menuprincipal.MainActivityAdapter
import com.example.appinflablesferoz.ui.menuprincipal.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import java.util.*

class ResumenInflableActivity : AppCompatActivity() {

    lateinit var viewModel: ResumenInflablesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen_inflable)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Detalles Generales"
        }

        viewModel = ViewModelProvider(this).get(ResumenInflablesViewModel::class.java)

        val id = intent.extras?.get("id") as String

        Toast.makeText(this, "Hola:::" + id, Toast.LENGTH_LONG).show()


        val carousel: ImageCarousel = findViewById(R.id.carouselResumenInflable)

        /*val list = mutableListOf<CarouselItem>()

        val headers = mutableMapOf<String, String>()
        headers["header_key"] = "header_value"

        val carouselItem1 = CarouselItem(
            imageUrl = "https://images.unsplash.com/photo-1549577434-d7615fd4ceac?w=1080",
            caption = "Photo by Jeremy Bishop on Unsplash",
            headers = headers
        )
        val carouselItem2 = CarouselItem(
            imageUrl = "https://images.unsplash.com/photo-1549577434-d7615fd4ceac?w=1080",
            headers = headers
        )

        list.add(carouselItem1)
        list.add(carouselItem2)*/
/*// Image URL with caption
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080",
                caption = "Photo by Aaron Wu on Unsplash"
            )
        )

// Just image URL
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            )
        )

// Image drawable with caption
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.loading,
                caption = "Photo by Kimiya Oveisi on Unsplash"
            )
        )

// Just image drawable
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.jar_loading
            )
        )

// ...

        carousel.addData(list)*/
        viewModel.getDatosResumenInflables(id).observe(this, androidx.lifecycle.Observer {
            carousel.addData(it)
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