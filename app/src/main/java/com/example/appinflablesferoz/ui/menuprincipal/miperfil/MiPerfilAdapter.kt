package com.example.appinflablesferoz.ui.menuprincipal.miperfil

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
import com.example.appinflablesferoz.models.Experiencias
import com.google.firebase.auth.UserInfo
import kotlinx.android.synthetic.main.item_row_scroll_image.view.*

class MiPerfilAdapter(private val context: Context): RecyclerView.Adapter<MiPerfilAdapter.MainViewHolder>() {

    private val PROVEEDOR_DESCONOCIDO = "Proveedor desconocido"
    private val PASSWORD_FIREBASE = "password"
    private val FIREBASE = "firebase" //  new

    private val FACEBOOK = "facebook.com"
    private val GOOGLE = "google.com"

    private var providers: List<UserInfo?>? = null
    fun ProviderAdapter(providers: List<UserInfo?>?) {
        this.providers = providers
    }


    private var dataList = mutableListOf<Experiencias>()
    fun setListData(data:MutableList<Experiencias>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiPerfilAdapter.MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_scroll_image,parent,false)
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

    override fun onBindViewHolder(holder: MiPerfilAdapter.MainViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(experiencias: Experiencias){
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()

            Glide.with(context).load(experiencias.imageUrl).apply(options).into(itemView.imgCircleScrollImage)

            itemView.txtTituloScrollImage.text = experiencias.inflable


        }
    }
}