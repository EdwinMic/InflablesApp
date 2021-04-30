package com.example.appinflablesferoz.ui.menuprincipal.inflables.resumeninflable

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appinflablesferoz.domain.network.Repo
import com.example.appinflablesferoz.models.Carrusel
import com.example.appinflablesferoz.models.Inflables
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class ResumenInflablesViewModel: ViewModel() {

    private val repo = Repo()

    //fun fetchUserData(context:Context):LiveData<MutableList<Usuario>> {

    fun getDatosResumenInflables(referencia:String):LiveData<MutableList<CarouselItem>> {
        Log.e("LOGeeeee","getDatosResumenInflables::::" +referencia)
        val mutableDataInflables = MutableLiveData<MutableList<CarouselItem>>()
        repo.retornarDatosResumenInflables(referencia).observeForever { userList ->
            mutableDataInflables.value = userList
        }
        return mutableDataInflables
    }

}