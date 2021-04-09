package com.example.appinflablesferoz.ui.menuprincipal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(): ViewModel(){


    private val _urlImagen = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fcocodrilomenuprincipal.jpg?alt=media&token=5c5a7072-73f0-4acc-97c4-6681b022fceb"
    }
    val urlImage: LiveData<String> = _urlImagen
}