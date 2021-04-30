package com.example.appinflablesferoz.ui.menuprincipal.inflables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appinflablesferoz.domain.network.Repo
import com.example.appinflablesferoz.models.Inflables

class InflablesViewModel: ViewModel() {

    private val repo = Repo()

    //fun fetchUserData(context:Context):LiveData<MutableList<Usuario>> {

    fun getDatosInflables():LiveData<MutableList<Inflables>> {
        val mutableDataInflables = MutableLiveData<MutableList<Inflables>>()
        repo.retornarDatosInflables().observeForever { userList ->
            mutableDataInflables.value = userList
        }
        return mutableDataInflables
    }

}