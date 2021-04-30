package com.example.appinflablesferoz.ui.menuprincipal

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
import kotlinx.android.synthetic.main.item_row_scroll_image.view.*


class MainActivityAdapter(val inflableClick: (String) -> Unit): RecyclerView.Adapter<MainActivityAdapter.MainViewHolder>()  {

    private var dataList = mutableListOf<Experiencias>()

    fun setListData(data:MutableList<Experiencias>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_scroll_image,parent,false)
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
        val experiencias = dataList[position]
        holder.bindView(experiencias)
        holder.itemView.setOnClickListener { inflableClick("" + experiencias.inflable) }
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(experiencias: Experiencias){
            val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()


            Glide.with(itemView.context)
                .load(experiencias.imageUrl)
                .apply(options)
                .placeholder(R.drawable.giphy)
                .into(itemView.imgCircleScrollImage)

            itemView.txtTituloScrollImage.text = experiencias.inflable


        }
    }


    /*inner class MainViewHolder(itemView: View) RecyclerView.ViewHolder(itemView){
        fun bindView(experiencias: Experiencias){

        }
    }*/

}