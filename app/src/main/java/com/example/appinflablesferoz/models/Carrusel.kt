package com.example.appinflablesferoz.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Carrusel(
    var id:String? = null,
    var nombre:String? = null,
    var imageUrl:String? = null
) {

}
