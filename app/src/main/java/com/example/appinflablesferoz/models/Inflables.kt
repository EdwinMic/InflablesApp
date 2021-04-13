package com.example.appinflablesferoz.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Inflables(
    var id:String? = null,
    var nombre:String? = null,
    var medidas:String? = null,
    var precio: Int? = null,
    var telefono: String? = null,
    var descripcion:String? = null,
    var imageUrl:String? = null
) {


    /*constructor()

    constructor(
        id: String?,
        nombre: String?,
        medidas: String?,
        precio: Integer?,
        descripcion: String?,
        imageUrl: String?
    ) {
        this.id = id
        this.nombre = nombre
        this.medidas = medidas
        this.precio = precio
        this.descripcion = descripcion
        this.imageUrl = imageUrl
    }*/

}
