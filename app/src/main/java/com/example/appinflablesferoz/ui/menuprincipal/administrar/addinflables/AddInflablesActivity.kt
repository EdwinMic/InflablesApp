package com.example.appinflablesferoz.ui.menuprincipal.administrar.addinflables

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_inflables.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddInflablesActivity : AppCompatActivity() {

    //Variables Camaras/Galeria

    //Variables Camaras/Galeria
    private val RP_CAMERA = 121
    private val RC_CAMERA = 22
    private val RC_GALLERY = 21
    private val RP_STORAGE = 122

    private val MY_PHOTO = "my_photo"
    private var mCurrentPhotoPath: String? = null
    val REQUEST_IMAGE_CAPTURE = 1
    private var mPhotoSelectedUri: Uri? = null

    private val mStorageReference: StorageReference? = null
    private val PATH_PROFILE = "Appbitacoras"
    private val mDataBaseReference: DatabaseReference? = null
    private val PATH_PHOTO_URL = "photoUrl"
    var banderaFoto = false

    //Photo DocGoogle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inflables)

        val btnPhoto = findViewById<MaterialButton>(R.id.mtbtnPhoto) as MaterialButton
        val btnGallery = findViewById<MaterialButton>(R.id.mtbtnGaleria)

        iniciarListasFB()

        btnPhoto.setOnClickListener {
            Toast.makeText(this,"Hola", Toast.LENGTH_LONG).show()
            Log.e("LOG","TEsst")
            checkPermissionToApp(Manifest.permission.CAMERA,RP_CAMERA)
        }

        btnGallery.setOnClickListener {
            checkPermissionToApp(Manifest.permission.READ_EXTERNAL_STORAGE,RP_STORAGE)
        }

    }

    fun iniciarListasFB() {
        val listPerfiles = arrayOf(
            "Castillo Cocodrilo",
            "Escaladora",
            "Catillo Aventura",
            "Castillo Woody"
        )
        val adapterPErfiles: ArrayAdapter<*> = ArrayAdapter(this,R.layout.item_menu_dropdown,listPerfiles)

        actvPerfilUsuarioEmpleado.setAdapter(adapterPErfiles)

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
            imgTomada.setImageBitmap(imageBitmap)
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
                        imgTomada.setImageBitmap(bitmap)
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
                        val options = RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        /*Picasso.get()
                            .load(bitmap)
                            .resize(88, 88)
                            .centerCrop()
                            .into(imgFotoMenuInflables)*/

                        Glide.with(this)
                            .load(bitmap)
                            .apply(options)
                            .into(imgTomada)


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

}