package com.example.appinflablesferoz.ui.menuprincipal.administrar.addnuevasexperiencias

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Inflables
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_nuevas_experiencias.*
import java.util.*

class AddNuevasExperienciasActivity : AppCompatActivity() {

    var calificacion = 0;

    private val RP_CAMERA = 121
    private val RC_CAMERA = 22
    private val RC_GALLERY = 21
    private val RP_STORAGE = 122

    private val MY_PHOTO = "my_photo"
    private var mCurrentPhotoPath: String? = null
    val REQUEST_IMAGE_CAPTURE = 1
    private var mPhotoSelectedUri: Uri? = null

    private var mStorageReference: StorageReference? = null
    private val PATH_PROFILE = "Inflables"
    private var mDataBaseStorageReference: DatabaseReference? = null
    private val PATH_PHOTO_URL = "photoUrl"
    var banderaFoto = false

    //Firebase REaltime
    //private lateinit var database: DatabaseReference
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    //Model
    var id = UUID.randomUUID().toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nuevas_experiencias)

        iniciarFirebase()
        inicirListInflables()


        btnCalificacion1.setOnClickListener(View.OnClickListener {
            btnCalificacion1.setImageResource(R.drawable.ic_star_complet_selected)
            btnCalificacion2.setImageResource(R.drawable.ic_star)
            btnCalificacion3.setImageResource(R.drawable.ic_star)
            btnCalificacion4.setImageResource(R.drawable.ic_star)
            btnCalificacion5.setImageResource(R.drawable.ic_star)
            calificacion = 1
        })

        btnCalificacion2.setOnClickListener(View.OnClickListener {
            btnCalificacion1.setImageResource(R.drawable.ic_star)
            btnCalificacion2.setImageResource(R.drawable.ic_star_complet_selected)
            btnCalificacion3.setImageResource(R.drawable.ic_star)
            btnCalificacion4.setImageResource(R.drawable.ic_star)
            btnCalificacion5.setImageResource(R.drawable.ic_star)
            calificacion = 2
        })

        btnCalificacion3.setOnClickListener(View.OnClickListener {
            btnCalificacion1.setImageResource(R.drawable.ic_star)
            btnCalificacion2.setImageResource(R.drawable.ic_star)
            btnCalificacion3.setImageResource(R.drawable.ic_star_complet_selected)
            btnCalificacion4.setImageResource(R.drawable.ic_star)
            btnCalificacion5.setImageResource(R.drawable.ic_star)
            calificacion = 3
        })

        btnCalificacion4.setOnClickListener(View.OnClickListener {
            btnCalificacion1.setImageResource(R.drawable.ic_star)
            btnCalificacion2.setImageResource(R.drawable.ic_star)
            btnCalificacion3.setImageResource(R.drawable.ic_star)
            btnCalificacion4.setImageResource(R.drawable.ic_star_complet_selected)
            btnCalificacion5.setImageResource(R.drawable.ic_star)
            calificacion = 4
        })
        btnCalificacion5.setOnClickListener(View.OnClickListener {
            btnCalificacion1.setImageResource(R.drawable.ic_star)
            btnCalificacion2.setImageResource(R.drawable.ic_star)
            btnCalificacion3.setImageResource(R.drawable.ic_star)
            btnCalificacion4.setImageResource(R.drawable.ic_star)
            btnCalificacion5.setImageResource(R.drawable.ic_star_complet_selected)
            calificacion = 5
        })



    }

    private fun iniciarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference


        mStorageReference = FirebaseStorage.getInstance().reference
        mDataBaseStorageReference = firebaseDatabase!!.reference
    }


    private fun inicirListInflables(){


        val listPerfiles = arrayOf(
            "Castillo Cocodrilo",
            "Escaladora",
            "Castillo Aventura",
            "Castillo Woddy"
        )

        val adapterPerfiles: ArrayAdapter<*> = ArrayAdapter(this,R.layout.item_menu_dropdown,listPerfiles)
        actvListAddExperienciaInflables.setAdapter(adapterPerfiles)
    }




}