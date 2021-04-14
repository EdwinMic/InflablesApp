package com.example.appinflablesferoz.ui.menuprincipal.inflables

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Inflables
import kotlinx.android.synthetic.main.item_row_inflables.view.*

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
        fun bindView(user: Inflables){
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()

            Glide.with(context).load(user.imageUrl).apply(options).into(itemView.imgCircle)
            itemView.txtTitulo.text = user.nombre
            itemView.txtMedidas.text = "Medidas: " + user.medidas
            itemView.txtPrecio.text = "Precio: $" + user.precio

            itemView.txtDescripcion.text = user.descripcion

            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fphone.png?alt=media&token=f66d4958-29bd-44af-8b29-e50da5569b0e")
                .apply(options).into(itemView.btnCall)
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fwhatsapp.png?alt=media&token=99ab1e09-10b3-4a2a-bc2b-c1a059840c9b")
                .apply(options).into(itemView.btnWhats)

        }
    }


}