package com.example.appinflablesferoz.domain.network

import android.content.Intent
import android.icu.text.RelativeDateTimeFormatter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.models.Carrusel
import com.example.appinflablesferoz.models.Experiencias
import com.example.appinflablesferoz.models.Inflables
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import java.io.Serializable

class Repo {


    fun retornarDatosInflables(): LiveData<MutableList<Inflables>> {
        Log.e("post","Entraste")
        val mutableData = MutableLiveData<MutableList<Inflables>>()
        val rootRef = FirebaseDatabase.getInstance().reference
        val friendsRef = rootRef.child("Inflables")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listData = mutableListOf<Inflables>()
                for (ds in dataSnapshot.children) {
                    Log.e("post","Children:" + ds.children)
                    Log.e("post","Value:" + ds.value)
                    val post: Inflables? = ds.getValue(Inflables::class.java)
                    if (post != null) {
                        Log.e("post","Value:" + post.nombre)
                        val usuario = Inflables(post.id.toString()!!, post.nombre.toString()!!,post.medidas.toString()!!,post.precio!!,post.telefono.toString()!!,post.descripcion.toString()!!, post.imageUrl.toString()!!)
                        listData.add(usuario)
                    }

                }
                mutableData.value = listData

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        friendsRef.addListenerForSingleValueEvent(eventListener)
        return mutableData
    }


    fun retornarDatosResumenInflables(referencia:String): LiveData<MutableList<CarouselItem>> {
        Log.e("post","EntrasteResumen" + referencia)
        //val mutableData = MutableLiveData<MutableList<Carrusel>>()
        val mutableData = MutableLiveData<MutableList<CarouselItem>>()
        val list = mutableListOf<CarouselItem>()
        val rootRef = FirebaseDatabase.getInstance().reference
        val friendsRef = rootRef.child(referencia)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listData = mutableListOf<Carrusel>()
                for (ds in dataSnapshot.children) {
                    Log.e("post","Children:" + ds.children)
                    Log.e("post","Value:" + ds.value)
                    val post: Carrusel? = ds.getValue(Carrusel::class.java)
                    if (post != null) {
                        Log.e("post","ResumenInfl:" + post.imageUrl)
                        //val usuario = Carrusel(post.id.toString()!!, post.nombre.toString()!!,post.imageUrl.toString()!!)
                        //listData.add(usuario)

                        val headers = mutableMapOf<String, String>()
                        headers["header_key"] = "header_value"

                        /*val carouselItem1 = CarouselItem(
                            imageUrl = "https://images.unsplash.com/photo-1549577434-d7615fd4ceac?w=1080",
                            caption = "Photo by Jeremy Bishop on Unsplash",
                            headers = headers
                        )*/
                        list.add(
                            CarouselItem(
                                imageUrl = post.imageUrl,
                                caption = post.nombre,
                                headers = headers
                            )
                        )
                        //listData.add(list)
                    }

                }
                mutableData.value = list

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        friendsRef.addListenerForSingleValueEvent(eventListener)
        return mutableData
    }


    fun retornarDataExperiencias(bandera: String): LiveData<MutableList<Experiencias>> {
        Log.e("post","Entraste")
        val mutableData = MutableLiveData<MutableList<Experiencias>>()
        val rootRef = FirebaseDatabase.getInstance().reference
        val friendsRef = rootRef.child("Experiencias")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listData = mutableListOf<Experiencias>()
                for (ds in dataSnapshot.children) {
                    Log.e("post","Children:" + ds.children)
                    Log.e("post","Value:" + ds.value)
                    val post: Experiencias? = ds.getValue(Experiencias::class.java)
                    if (post != null) {
                        Log.e("post","Value:" + post.inflable)

                        if(bandera.equals("Usuario")){
                            if(post.estatus.equals("Autorizado")){
                                val experiencias = Experiencias(post.id.toString()!!, post.inflable.toString()!!,post.fecha.toString()!!, post.estatus.toString()!!,post.calificacion!!, post.imageUrl.toString()!!)
                                listData.add(experiencias)
                            }
                        }

                        if(bandera.equals("Administrar")){
                            if(post.estatus.equals("Pendiente")){
                                val experiencias = Experiencias(post.id.toString()!!, post.inflable.toString()!!,post.fecha.toString()!!, post.estatus.toString()!!,post.calificacion!!, post.imageUrl.toString()!!)
                                listData.add(experiencias)
                            }
                        }


                    }

                }
                mutableData.value = listData
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        friendsRef.addListenerForSingleValueEvent(eventListener)
        return mutableData
    }


    //fun retornarDataExperiencias(bandera: String): LiveData<MutableList<Experiencias>> {
    fun retornarDataExperienciasCarrusel(bandera: String): LiveData<MutableList<CarouselItem>> {
        Log.e("post","Entraste")
        val list = mutableListOf<CarouselItem>()
        //val mutableData = MutableLiveData<MutableList<Experiencias>>()
        val mutableData = MutableLiveData<MutableList<CarouselItem>>()
        val rootRef = FirebaseDatabase.getInstance().reference
        val friendsRef = rootRef.child("Experiencias")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listData = mutableListOf<Experiencias>()
                for (ds in dataSnapshot.children) {
                    Log.e("post","Children:" + ds.children)
                    Log.e("post","Value:" + ds.value)
                    val post: Experiencias? = ds.getValue(Experiencias::class.java)
                    if (post != null) {
                        Log.e("post","Value:" + post.inflable)

                        if(bandera.equals("Usuario")){
                            if(post.estatus.equals("Autorizado")){
                                val experiencias = Experiencias(post.id.toString()!!, post.inflable.toString()!!,post.fecha.toString()!!, post.estatus.toString()!!,post.calificacion!!, post.imageUrl.toString()!!)
                                list.add(
                                    CarouselItem(
                                        imageUrl = post.imageUrl,
                                        caption = post.inflable
                                    )
                                )
                                listData.add(experiencias)
                            }
                        }

                        if(bandera.equals("Administrar")){
                            if(post.estatus.equals("Pendiente")){
                                val experiencias = Experiencias(post.id.toString()!!, post.inflable.toString()!!,post.fecha.toString()!!, post.estatus.toString()!!,post.calificacion!!, post.imageUrl.toString()!!)
                                list.add(
                                    CarouselItem(
                                        imageUrl = post.imageUrl,
                                        caption = post.inflable
                                    )
                                )
                                listData.add(experiencias)
                            }
                        }


                    }

                }
                //mutableData.value = listData
                mutableData.value = list

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        friendsRef.addListenerForSingleValueEvent(eventListener)
        return mutableData
    }


}