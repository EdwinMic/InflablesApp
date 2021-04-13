package com.example.appinflablesferoz.domain.network

import android.content.Intent
import android.icu.text.RelativeDateTimeFormatter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appinflablesferoz.models.Inflables
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.Serializable

class Repo {


    fun retornarRelacionTablas(): LiveData<MutableList<Inflables>> {
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


}