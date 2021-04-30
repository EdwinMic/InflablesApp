package com.example.appinflablesferoz.ui.menuprincipal.miperfil.addnuevasexperiencias

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.example.appinflablesferoz.models.Experiencias
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_nuevas_experiencias.*
import kotlinx.android.synthetic.main.activity_add_nuevas_experiencias.toolbar
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
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
    private val PATH_PROFILE = "Experiencias"
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

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Agregar Experiencia"
        }

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

        mtbtnPhotoAddExperiencia.setOnClickListener {
            Toast.makeText(this,"Hola", Toast.LENGTH_LONG).show()
            Log.e("LOG","TEsst")
            checkPermissionToApp(Manifest.permission.CAMERA,RP_CAMERA)
        }

        mtbtnGaleriaAddExperiencia.setOnClickListener {
            checkPermissionToApp(Manifest.permission.READ_EXTERNAL_STORAGE,RP_STORAGE)
        }

        fabAddExperienciaSave.setOnClickListener(View.OnClickListener {
            if(validarCampos()){
                guardarImagen()
            }
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

    private fun guardarImagen() {

        try {
            val profileReference =
                mStorageReference!!.child(PATH_PROFILE)

            //Nombre de la imagen
            val photoReference =profileReference.child(id)
            photoReference.putFile(mPhotoSelectedUri!!)
                .addOnCompleteListener {
                    //progressBar.setVisibility(View.GONE);
                }
                .addOnSuccessListener { taskSnapshot ->
                    //Snackbar.make(findViewById(android.R.id.content),"Registro guardado", Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(AddRegistroActivity.this, "Imagen cargada", Toast.LENGTH_SHORT).show();
                    taskSnapshot.storage.downloadUrl
                        .addOnSuccessListener { uri ->
                            saveOrEdit(uri)
                            //btnDelete.setVisibility(View.VISIBLE);
                            //mTextMessage.setText(R.string.main_message_done);
                        }
                }
                .addOnFailureListener {
                    //Snackbar.make(container, R.string.main_message_upload_error, Snackbar.LENGTH_LONG).show();
                }
        } catch (e: java.lang.Exception) {
            Snackbar.make(containerAddExperiencias,R.string.main_message_add_image, Snackbar.LENGTH_LONG).show()
        }

    }


    //AddData RealtimeDatabase
    private fun saveOrEdit(uri: Uri?) {


        val sdf = SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.getDefault())
        val currentDateandTime = sdf.format(Date())

        val inflable = actvListAddExperienciaInflables.text.toString()
        val mAddExperiencia = Experiencias(
            id,
            inflable,
            "" + currentDateandTime,
            "Pendiente",
            calificacion,
            uri.toString()
        )

        databaseReference = firebaseDatabase!!.reference
            .child(PATH_PROFILE)
            .child(id)

        databaseReference!!.setValue(mAddExperiencia).addOnSuccessListener {
            Snackbar.make(containerAddExperiencias,"Datos registrados",Snackbar.LENGTH_LONG).show()
            super.onBackPressed()
        }
    }

    private fun validarCampos(): Boolean {
        var isValid = true

        if (calificacion > 0) {
            tvValoracion.setText(getString(R.string.txt_field_menu_valoranos))
            tvValoracion.setTextColor(Color.parseColor("#000000"))
        } else {
            tvValoracion.setText(getString(R.string.common_validate_field_add_valoracion))
            tvValoracion.setTextColor(Color.parseColor("#d50000"))
            isValid = false

        }
        if (actvListAddExperienciaInflables.text.toString().trim().isNotEmpty()) {
            tilListAddExperienciaInflables.setError(null)
        } else {
            tilListAddExperienciaInflables.setError(getString(R.string.common_validate_field_required))
            actvListAddExperienciaInflables.requestFocus()
            isValid = false
        }

        return isValid
    }


    private fun checkPermissionToApp(permisionStr: String,requestPermision: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,permisionStr) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,arrayOf(permisionStr),requestPermision)
                return
            }
        }
        when (requestPermision) {
            RP_STORAGE -> fromGallery()
            RP_CAMERA -> dispatchTakePictureIntent()
        }
    }

    private fun fromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RC_GALLERY)
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            val photoFile: File?
            photoFile = createImageFile()
            if (photoFile != null) {
                val photoUri =
                    FileProvider.getUriForFile(this, "com.example.appinflablesferoz", photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent,RC_CAMERA)
            }
        }

        /*Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }*/
    }

    private fun createImageFile(): File? {
        val timeStamp =
            SimpleDateFormat("dd-MM-yyyy_HHmmss", Locale.ROOT)
                .format(Date())
        val imageFileName: String = MY_PHOTO + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image: File? = null
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir)
            mCurrentPhotoPath = image.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data!!.extras
            val imageBitmap = extras!!["data"] as Bitmap?
            imgTomadaAddExperiencia.setImageBitmap(imageBitmap)
        }


        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_GALLERY -> if (data != null) {
                    mPhotoSelectedUri = data.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            mPhotoSelectedUri
                        )
                        imgTomadaAddExperiencia.setImageBitmap(bitmap)
                        //btnDelete.setVisibility(View.GONE);
                        //mTextMessage.setText(R.string.main_message_question_upload);
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                RC_CAMERA -> {

                    /*Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");*/
                    mPhotoSelectedUri = addPickGallery()

                    try {

                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, mPhotoSelectedUri)
                        val options = RequestOptions().centerCrop().diskCacheStrategy(
                            DiskCacheStrategy.ALL)
                        /*Picasso.get()
                            .load(bitmap)
                            .resize(88, 88)
                            .centerCrop()
                            .into(imgFotoMenuInflables)*/

                        Glide.with(this)
                            .load(bitmap)
                            .apply(options)
                            .into(imgTomadaAddExperiencia)


                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun addPickGallery(): Uri? {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val file = File(mCurrentPhotoPath)
        val contentUri = Uri.fromFile(file)
        mediaScanIntent.data = contentUri
        this.sendBroadcast(mediaScanIntent)
        mCurrentPhotoPath = null
        return contentUri
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home ->

                super.onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }
}