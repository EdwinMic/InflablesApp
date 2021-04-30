package com.example.appinflablesferoz.ui.menuprincipal.inflables

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Inflables
import com.example.appinflablesferoz.ui.menuprincipal.inflables.resumeninflable.ResumenInflableActivity
import com.example.appinflablesferoz.ui.menuprincipal.miperfil.MiPerfilActivity
import kotlinx.android.synthetic.main.item_row_inflables.view.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class InflablesAdapter(val inflableClick: (String) -> Unit): RecyclerView.Adapter<InflablesAdapter.SearchViewHolder>() {
    var inflableList: List<Inflables> = emptyList<Inflables>()

    fun setData(list: List<Inflables>){
        inflableList = list
        notifyDataSetChanged()
    }

    /*fun setData(list: List<ServicioPrueba>){
        servicioPruebaList = list
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_inflables, parent,false))
    }

    override fun getItemCount(): Int {
        return inflableList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val inflables = inflableList[position]

        val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()

        Glide.with(holder.itemView.context).load(inflables.imageUrl).apply(options).into(holder.itemView.imgCircle)
        holder.itemView.txtTitulo.text = "#${position + 1} - ${inflables.nombre}"
        //holder.itemView.txtTitulo.text = inflables.nombre
        holder.itemView.txtMedidas.text = "Medidas: " + inflables.medidas
        holder.itemView.txtPrecio.text = "Precio: $" + inflables.precio
        holder.itemView.txtDescripcion.text = inflables.descripcion


        holder.itemView.setOnClickListener { inflableClick("" + inflables.nombre) }
    }

    private fun getDateTime(s: Long): String {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}







/*

class InflablesAdapter(private val context:Context): RecyclerView.Adapter<InflablesAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Inflables>()
    fun setListData(data:MutableList<Inflables>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_inflables,parent,false)
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
        fun bindView(inflable: Inflables){
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()

            Glide.with(context).load(inflable.imageUrl).apply(options).into(itemView.imgCircle)
            itemView.txtTitulo.text = inflable.nombre
            itemView.txtMedidas.text = "Medidas: " + inflable.medidas
            itemView.txtPrecio.text = "Precio: $" + inflable.precio

            itemView.txtDescripcion.text = inflable.descripcion

            */
/*Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fphone.png?alt=media&token=f66d4958-29bd-44af-8b29-e50da5569b0e")
                .apply(options).into(itemView.btnCall)
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fwhatsapp.png?alt=media&token=99ab1e09-10b3-4a2a-bc2b-c1a059840c9b")
                .apply(options).into(itemView.btnWhats)*//*


            itemView.btnApartar.setOnClickListener {
                val intent = Intent( context, MiPerfilActivity::class.java)
                context.startActivity(intent)
            }

            itemView.btnBuscarResumenInfl.setOnClickListener {
                val message = "Hola"
                val intent = Intent(this, ResumenInflableActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
            }

            */
/*itemView.btnWhats.setOnClickListener {
                val _intencion = Intent("android.intent.action.MAIN")
                _intencion.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
                _intencion.putExtra(
                    "jid",
                    PhoneNumberUtils.stripSeparators("525512365197")
                        .toString() + "@s.whatsapp.net"
                )
                context.startActivity(_intencion)
            }*//*

        }
    }


}*/
