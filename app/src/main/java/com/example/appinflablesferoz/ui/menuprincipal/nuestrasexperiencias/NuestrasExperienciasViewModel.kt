package com.example.appinflablesferoz.ui.menuprincipal.nuestrasexperiencias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appinflablesferoz.domain.network.Repo
import com.example.appinflablesferoz.models.Experiencias
import com.example.appinflablesferoz.models.Inflables

class NuestrasExperienciasViewModel: ViewModel() {

    private val repo = Repo()

    //fun fetchUserData(context:Context):LiveData<MutableList<Usuario>> {
    fun fetchDataExperiencias():LiveData<MutableList<Experiencias>> {
        val mutableData = MutableLiveData<MutableList<Experiencias>>()
        repo.retornarDataExperiencias("Usuario").observeForever { userList ->
            mutableData.value = userList
        }
        return mutableData
    }

}