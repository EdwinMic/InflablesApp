package com.example.appinflablesferoz.ui.menuprincipal.administrar.menu.administrarexperiencias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appinflablesferoz.domain.network.Repo
import com.example.appinflablesferoz.models.Experiencias
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class AdministrarExperienciasViewModel: ViewModel() {

    private val repo = Repo()
    fun fetchDataAdministrarExperiencias(): LiveData<MutableList<Experiencias>> {
        val mutableData = MutableLiveData<MutableList<Experiencias>>()
        repo.retornarDataExperiencias("Administrar").observeForever { userList ->
            mutableData.value = userList
        }
        return mutableData
    }
}