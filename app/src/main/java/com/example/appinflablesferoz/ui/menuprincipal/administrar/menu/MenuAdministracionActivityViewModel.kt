package com.example.appinflablesferoz.ui.menuprincipal.administrar.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuAdministracionActivityViewModel(): ViewModel() {

    private val _urlImagenTituloInflable = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fcocodrilo_menu.jpg?alt=media&token=bec618e4-420b-4a80-914b-134e55052b36"
    }
    val urlImagenTituloInflable: LiveData<String> = _urlImagenTituloInflable

    private val _urlImagenTituloContactos = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fphone.png?alt=media&token=f66d4958-29bd-44af-8b29-e50da5569b0e"
    }
    val urlImagenTituloContactos: LiveData<String> = _urlImagenTituloContactos

    private val _urlImagenTituloUbicanos = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fmapa.png?alt=media&token=b5435592-72cf-4dae-bb5b-537631405123"
    }
    val urlImagenTituloUbicanos: LiveData<String> = _urlImagenTituloUbicanos

    private val _urlImagenTituloExperiencias = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fgaleria-de-fotos.png?alt=media&token=be1fe208-0daf-41ad-bfae-8b98860c702f"
    }
    val urlImagenTituloExperiencias: LiveData<String> = _urlImagenTituloExperiencias

    private val _urlImagenTituloBuzon = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fcorreo.png?alt=media&token=9a542f85-3a66-4242-884f-a1c20d63aa5c"
    }
    val urlImagenTituloBuzon: LiveData<String> = _urlImagenTituloBuzon

    private val _urlImagenTituloAdministrar = MutableLiveData<String>().apply {
        value = "https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fconfiguraciones.png?alt=media&token=34d698a8-4cf6-433b-8f0e-17d474250182"
    }
    val urlImagenTituloAdministrar: LiveData<String> = _urlImagenTituloAdministrar

}