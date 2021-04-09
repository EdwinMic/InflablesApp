package com.example.appinflablesferoz.ui.menuprincipal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModelMainActivity: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelMainActivity = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //val imgFotoMenuInflables: CircleImageView = findViewById(R.id.imgFotoMenuInflables)

        /*Picasso.get()
            .load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fcocodrilomenuprincipal.jpg?alt=media&token=5c5a7072-73f0-4acc-97c4-6681b022fceb")
            .into(imgFotoMenuInflables)
*/
        Picasso.get()
            .load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fcocodrilomenuprincipal.jpg?alt=media&token=5c5a7072-73f0-4acc-97c4-6681b022fceb")
            .resize(88, 88)
            .centerCrop()
            .into(imgFotoMenuInflables)

        Picasso.get()
            .load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Ftelefono.png?alt=media&token=a8c74336-7d56-4637-a130-cf0fbab4174b")
            .resize(88, 88)
            .centerCrop()
            .into(imgFotoMenuInflables1)
        initUI()
    }


    fun initUI(){


        /*viewModelMainActivity.urlImage.observe(this, Observer {
            Glide.with(this).load(it)
                .into(imgFotoMenuInflables)

        })*/
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