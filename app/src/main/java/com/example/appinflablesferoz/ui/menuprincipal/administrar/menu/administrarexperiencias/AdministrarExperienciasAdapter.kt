package com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.administrarexperiencias

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Experiencias
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.item_row_administrar_experiencias.view.*
import java.util.*
import java.util.stream.Collectors.toMap


class AdministrarExperienciasAdapter(private val context: Context): RecyclerView.Adapter<AdministrarExperienciasAdapter.MainViewHolder>()  {


    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    private var dataList = mutableListOf<Experiencias>()
    fun setListData(data:MutableList<Experiencias>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_administrar_experiencias,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.e("dataList",""+dataList.size)
        return if(dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(experiencias: Experiencias){
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()

            Glide.with(context).load(experiencias.imageUrl).apply(options).into(itemView.imgCircleAdminExperiencias)


            itemView.txtInflableAdminExperiencias.text = experiencias.inflable
            itemView.txtFechaAdminExperiencias.text = experiencias.fecha

            if(experiencias.calificacion == 1){
                itemView.btnCalificacionAdminExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias2.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionAdminExperiencias3.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionAdminExperiencias4.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionAdminExperiencias5.setImageResource(R.drawable.ic_star)
            }

            if(experiencias.calificacion == 2){
                itemView.btnCalificacionAdminExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias3.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionAdminExperiencias4.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionAdminExperiencias5.setImageResource(R.drawable.ic_star)
            }
            if(experiencias.calificacion == 3){
                itemView.btnCalificacionAdminExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias3.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias4.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionAdminExperiencias5.setImageResource(R.drawable.ic_star)
            }

            if(experiencias.calificacion == 4){
                itemView.btnCalificacionAdminExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias3.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias4.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias5.setImageResource(R.drawable.ic_star)
            }
            if(experiencias.calificacion == 5){
                itemView.btnCalificacionAdminExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias3.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias4.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionAdminExperiencias5.setImageResource(R.drawable.ic_star_complet_selected)
            }

            itemView.btnApartarAdminExperiencias.setOnClickListener(View.OnClickListener {

                val experienciasSet = Experiencias()
                experienciasSet.id = experiencias.id
                experienciasSet.inflable = experiencias.inflable
                experienciasSet.fecha = experiencias.fecha
                experienciasSet.estatus = ""
                experienciasSet.calificacion = experiencias.calificacion
                experienciasSet.imageUrl = experiencias.imageUrl



                firebaseDatabase = FirebaseDatabase.getInstance()
                databaseReference = experiencias.id?.let { it1 ->
                    firebaseDatabase!!.reference
                        .child("Experiencias").child(it1)
                }

                val childUpdates = hashMapOf<String, Any>(
                    "estatus" to "Autorizado"

                )

                databaseReference?.updateChildren(childUpdates)


            })
        }
    }


    /*inner class MainViewHolder(itemView: View) RecyclerView.ViewHolder(itemView){
        fun bindView(experiencias: Experiencias){

        }
    }*/

}

