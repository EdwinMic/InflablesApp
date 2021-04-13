package com.example.appinflablesferoz.ui.menuprincipal.inflables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appinflablesferoz.domain.network.Repo
import com.example.appinflablesferoz.models.Inflables

class InflablesViewModel: ViewModel() {

    private val repo = Repo()

    //fun fetchUserData(context:Context):LiveData<MutableList<Usuario>> {
    fun fetchUserData():LiveData<MutableList<Inflables>> {
        val mutableData = MutableLiveData<MutableList<Inflables>>()
        repo.retornarRelacionTablas().observeForever { userList ->
            mutableData.value = userList
        }
        return mutableData
    }

}