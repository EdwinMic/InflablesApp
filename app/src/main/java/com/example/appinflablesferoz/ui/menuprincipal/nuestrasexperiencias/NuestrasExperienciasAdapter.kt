package com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Experiencias
import com.example.appinflablesferoz.models.Inflables
import kotlinx.android.synthetic.main.activity_add_nuevas_experiencias.*
import kotlinx.android.synthetic.main.item_row_experiencias.view.*
import kotlinx.android.synthetic.main.item_row_inflables.view.*


class NuestrasExperienciasAdapter(private val context: Context): RecyclerView.Adapter<NuestrasExperienciasAdapter.MainViewHolder>()  {

    private var dataList = mutableListOf<Experiencias>()
    fun setListData(data:MutableList<Experiencias>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_experiencias,parent,false)
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

            Glide.with(context).load(experiencias.imageUrl).apply(options).into(itemView.imgCircleExperiencias)
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fphone.png?alt=media&token=f66d4958-29bd-44af-8b29-e50da5569b0e")
                .apply(options).into(itemView.btnCallExperiencias)
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fwhatsapp.png?alt=media&token=99ab1e09-10b3-4a2a-bc2b-c1a059840c9b")
                .apply(options).into(itemView.btnWhatsExperiencias)

            itemView.txtInflableExperiencias.text = experiencias.inflable
            itemView.txtFechaExperiencias.text = experiencias.fecha

            if(experiencias.calificacion == 1){
                itemView.btnCalificacionExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias2.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionExperiencias3.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionExperiencias4.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionExperiencias5.setImageResource(R.drawable.ic_star)
            }

            if(experiencias.calificacion == 2){
                itemView.btnCalificacionExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias3.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionExperiencias4.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionExperiencias5.setImageResource(R.drawable.ic_star)
            }
            if(experiencias.calificacion == 3){
                itemView.btnCalificacionExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias3.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias4.setImageResource(R.drawable.ic_star)
                itemView.btnCalificacionExperiencias5.setImageResource(R.drawable.ic_star)
            }

            if(experiencias.calificacion == 4){
                itemView.btnCalificacionExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias3.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias4.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias5.setImageResource(R.drawable.ic_star)
            }
            if(experiencias.calificacion == 5){
                itemView.btnCalificacionExperiencias1.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias2.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias3.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias4.setImageResource(R.drawable.ic_star_complet_selected)
                itemView.btnCalificacionExperiencias5.setImageResource(R.drawable.ic_star_complet_selected)
            }
        }
    }


    /*inner class MainViewHolder(itemView: View) RecyclerView.ViewHolder(itemView){
        fun bindView(experiencias: Experiencias){

        }
    }*/

}