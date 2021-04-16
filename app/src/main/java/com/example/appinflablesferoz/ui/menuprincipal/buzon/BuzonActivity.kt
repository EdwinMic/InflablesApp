package com.example.appinflablesferoz.ui.menuprincipal.buzon

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Buzon
import com.example.appinflablesferoz.models.Inflables
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_inflables.*
import kotlinx.android.synthetic.main.activity_add_nuevas_experiencias.*
import kotlinx.android.synthetic.main.activity_buzon.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.imgDecorationBuzon
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.*
import kotlinx.android.synthetic.main.activity_nuestras_experiencias.toolbar
import java.text.SimpleDateFormat
import java.util.*

class BuzonActivity : AppCompatActivity() {

    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    var id = UUID.randomUUID().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buzon)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Quejas y Sugerencias"
        }

        initUI()

        iniciarFirebase()

        inicirListInflables()


        fabAddBuzon.setOnClickListener(View.OnClickListener {
            if(validarCampos()){
                saveOrEdit()
            }
        })

    }

    fun initUI() {

        Picasso.get()
            .load("https://firebasestorage.googleapis.com/v0/b/inflablesferoz.appspot.com/o/ImagenesPredeterminadas%2Fescritura.png?alt=media&token=f3857120-c721-4226-997a-d7180b77f846")
            .resize(88, 88)
            .centerCrop()
            .into(imgDecorationBuzon)

    }

    //iniciar firebase
    private fun iniciarFirebase(){

        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference


    }


    private fun inicirListInflables(){
        val listPerfiles = arrayOf(
            "Castillo Cocodrilo",
            "Escaladora",
            "Castillo Aventura",
            "Castillo Woddy"
        )

        val adapterPerfiles: ArrayAdapter<*> = ArrayAdapter(this,R.layout.item_menu_dropdown,listPerfiles)
        actvListAddInflableBuzon.setAdapter(adapterPerfiles)
    }


    private fun validarCampos(): Boolean {
        var isValid = true

        if (etAddComentarioBuzon.text.toString().trim().isNotEmpty()) {
            tilAddComentarioBuzon.setError(null)
        } else {
            tilAddComentarioBuzon.setError(getString(R.string.common_validate_field_required))
            etAddComentarioBuzon.requestFocus()
            isValid = false

        }

        return isValid
    }

    //AddData RealtimeDatabase
    private fun saveOrEdit() {

        val sdf = SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.getDefault())
        val currentDateandTime = sdf.format(Date())

        val mAddBuzon = Buzon(
            id,
            "" + actvListAddInflableBuzon.text.toString(),
            "" + etAddNombreBuzon.text.toString(),
            "" + currentDateandTime,
            "" + etAddComentarioBuzon.text.toString()

        )

        databaseReference = firebaseDatabase!!.reference
            .child("Buzon")
            .child(id)

        databaseReference!!.setValue(mAddBuzon).addOnSuccessListener {
            Snackbar.make(containerBuzon,"Datos registrados", Snackbar.LENGTH_LONG).setAction("OK" , View.OnClickListener {
                super.onBackPressed()
            }).show()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home ->

                super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }
}