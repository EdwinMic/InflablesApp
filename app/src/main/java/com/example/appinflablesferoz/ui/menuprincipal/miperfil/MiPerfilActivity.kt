package com.example.appinflablesferoz.ui.menuprincipal.miperfil

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appinflablesferoz.R
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_mi_perfil.*
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MiPerfilActivity : AppCompatActivity() {


    private var mFirebaseAuth: FirebaseAuth? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null

    private val RC_SIGN_IN = 123
    private val RC_FROM_GALLERY = 124

    private val PATH_PROFILE = "profile"
    private val MY_PHOTO_AUTH = "my_photo_auth"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_perfil)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Mi Perfil"
        }

        validarLogin()

        btnIniciarSesion.setOnClickListener(View.OnClickListener {
            iniciarSesion()
        })

        imgPhotoProfile.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, RC_FROM_GALLERY)
        })


        /*adapter = ProviderAdapter(ArrayList<E>())
        rvMiPerfil.setLayoutManager(LinearLayoutManager(this))
        rvMiPerfil.setAdapter(adapter)*/

    }

    private fun validarLogin(){
        mFirebaseAuth = FirebaseAuth.getInstance()
        mAuthStateListener = AuthStateListener { firebaseAuth ->
            //se ejecuta cada que se ejecute un cabio inicio o cierre de sesion
            val user = firebaseAuth.currentUser
            Log.e("user:" , "USER::" + user)
            if (user != null) {

                //onSetDataUser(user.getDisplayName(), user.getEmail(),PROVEEDOR_DESCONOCIDO);
                /*onSetDataUser(user.getDisplayName(), user.getEmail(),
                               user.getProviderData().get(0) != null ?
                                        user.getProviderData().get(0).getProviderId() : PROVEEDOR_DESCONOCIDO);*/
                Toast.makeText(this, "!=null",Toast.LENGTH_LONG).show()
                onSetDataUser(user.displayName, user.email, user.providerData)
                btnIniciarSesion.isEnabled = false
                Log.e("userphotoUrl","user.photoUrl::::" + user.photoUrl)
                if(user.photoUrl != null){
                    loadImage(user.photoUrl)
                }

            } else {

                btnIniciarSesion.isEnabled = true
                /* onSignedOutCleanup()
                 Toast.makeText(this, "=null",Toast.LENGTH_LONG).show()
                 *//*val facebookIdp: AuthUI.IdpConfig = AuthUI.IdpConfig.FacebookBuilder()
                    .setPermissions(Arrays.asList("user_friends", "user_gender"))
                    .build()*//*
                val googleIdp: AuthUI.IdpConfig = AuthUI.IdpConfig.GoogleBuilder()
                    .build()

                val customLayout: AuthMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.custom_view_login)
                    .setEmailButtonId(R.id.btnEmail)
                    .setGoogleButtonId(R.id.btnGoogle)
                    //.setFacebookButtonId(R.id.btnFacebook)
                    .setPhoneButtonId(R.id.btnPhone)
                    .setTosAndPrivacyPolicyId(R.id.tvPolicy)
                    .build()
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false) //.setTosUrl("https://es-la.facebook.com/")
                        *//*.setTosAndPrivacyPolicyUrls(
                            "https://www.udemy.com/especialidad-en-firebase-para-android-con-mvp-profesional",
                            "https://www.udemy.com/user/alain-nicolas-tello"
                        )*//*
                        .setAvailableProviders(
                            mutableListOf(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.PhoneBuilder().build(),
                                //facebookIdp,
                                googleIdp
                            )
                        )
                        //.setTheme(R.style.GreenTheme)
                        .setTheme(R.style.Base_MyTheme)
                        .setLogo(R.drawable.ic_star_selected)
                        //.setAuthMethodPickerLayout(customLayout)
                        .build(), RC_SIGN_IN
                )*/

            }
        }

        try {
            val info = packageManager.getPackageInfo(
                "com.example.appinflablesferoz",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d(
                    "KeyHash:",
                    Base64.encodeToString(md.digest(), Base64.DEFAULT)
                )
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }

    private fun iniciarSesion() {
        onSignedOutCleanup()
        Toast.makeText(this, "=null",Toast.LENGTH_LONG).show()
        /*val facebookIdp: AuthUI.IdpConfig = AuthUI.IdpConfig.FacebookBuilder()
            .setPermissions(Arrays.asList("user_friends", "user_gender"))
            .build()*/
        val googleIdp: AuthUI.IdpConfig = AuthUI.IdpConfig.GoogleBuilder()
            .build()

        val customLayout: AuthMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.custom_view_login)
            .setEmailButtonId(R.id.btnEmail)
            .setGoogleButtonId(R.id.btnGoogle)
            //.setFacebookButtonId(R.id.btnFacebook)
            .setPhoneButtonId(R.id.btnPhone)
            .setTosAndPrivacyPolicyId(R.id.tvPolicy)
            .build()
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false) //.setTosUrl("https://es-la.facebook.com/")
                /*.setTosAndPrivacyPolicyUrls(
                    "https://www.udemy.com/especialidad-en-firebase-para-android-con-mvp-profesional",
                    "https://www.udemy.com/user/alain-nicolas-tello"
                )*/
                .setAvailableProviders(
                    mutableListOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.PhoneBuilder().build(),
                        //facebookIdp,
                        googleIdp
                    )
                )
                //.setTheme(R.style.GreenTheme)
                .setTheme(R.style.Base_MyTheme)
                .setLogo(R.drawable.ic_star_selected)
                .setAuthMethodPickerLayout(customLayout)
                .build(), RC_SIGN_IN
        )
    }

    private fun onSetDataUser(userName: String,email: String,providers: List<UserInfo?>) {
        tvUserName.text = userName
        tvEmail.text = email
        //adapter.setProviders(providers)
    }

    private fun loadImage(photoUrl: Uri) {
        val options = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
        Glide.with(this)
            .load(photoUrl)
            .apply(options)
            .placeholder(R.drawable.loading)
            .into(imgPhotoProfile)
    }

    /* private void onSignedOutCleanup() {
        onSetDataUser("", "", "");
    }*/

    private fun onSignedOutCleanup() {
        onSetDataUser("", "", ArrayList())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (requestCode == RC_SIGN_IN) {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Intentar de nuevo", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == RC_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            progresBar.visibility = View.VISIBLE
            if (true) {
                data!!.data?.let { uploadImageTask(it) }
            } else {
                uploadImageFile(data!!.data)
            }
        }
    }

    private fun uploadImageTask(selectedImageUri: Uri) {
        val storage = FirebaseStorage.getInstance()
        if (tvEmail.text.toString().trim { it <= ' ' } == "") {
        }
        val reference = storage.reference.child(PATH_PROFILE)
            .child(tvEmail.text.toString().trim { it <= ' ' })
        var bitmap: Bitmap
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
            bitmap = getResizedBitmap(bitmap, 1024)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val uploadTask = reference.putBytes(data)
            uploadTask.addOnProgressListener { taskSnapshot ->
                val progress =
                    100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        .toDouble()
                progresBar.progress = progress.toInt()
                tvProgress.text = String.format("%s%%", progress)
                tvProgress.animate().alpha(1f).duration = 200
            }
                .addOnCompleteListener {
                    progresBar.visibility = View.INVISIBLE
                    tvProgress.text = "Listo"
                    tvProgress.animate().alpha(0f).duration = 2000
                }
                .addOnSuccessListener {
                    reference.downloadUrl
                        .addOnSuccessListener { uri ->
                            val user =
                                FirebaseAuth.getInstance().currentUser
                            if (user != null) {
                                val request =
                                    UserProfileChangeRequest.Builder()
                                        .setPhotoUri(uri).build()
                                user.updateProfile(request).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        loadImage(user.photoUrl)
                                    }
                                }
                            }
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getResizedBitmap(bitmap: Bitmap, maxSize: Int): Bitmap {
        var width = bitmap.width
        var height = bitmap.height
        if (width <= maxSize && height <= height) {
            return bitmap
        }
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height / bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    private fun uploadImageFile(selectedImageUri: Uri?) {
        val storage = FirebaseStorage.getInstance()
        val reference =
            storage.reference.child(PATH_PROFILE).child(MY_PHOTO_AUTH)
        if (selectedImageUri != null) {
            reference.putFile(selectedImageUri)
                .addOnProgressListener { taskSnapshot ->
                    val progress =
                        100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                            .toDouble()
                    progresBar.progress = progress.toInt()
                    tvProgress.text = String.format("%s%%", progress)
                    tvProgress.animate().alpha(1f).duration = 200
                }
                .addOnCompleteListener {
                    progresBar.visibility = View.INVISIBLE
                    tvProgress.text = "Listo"
                    tvProgress.animate().alpha(0f).duration = 2000
                }
                .addOnSuccessListener {
                    reference.downloadUrl
                        .addOnSuccessListener { uri ->
                            val user =
                                FirebaseAuth.getInstance().currentUser
                            if (user != null) {
                                val request =
                                    UserProfileChangeRequest.Builder()
                                        .setPhotoUri(uri).build()
                                user.updateProfile(request).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        loadImage(user.photoUrl)
                                    }
                                }
                            }
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    override fun onResume() {
        super.onResume()
        //evita uso indebido del servicio, no gasta la pila del usuario y con miles de usuarios reduce los costos
        mFirebaseAuth!!.addAuthStateListener(mAuthStateListener)
    }

    override fun onPause() {
        super.onPause()
        //es recomendable quitarlo en el metodo onpause
        if (mAuthStateListener != null) {
            mFirebaseAuth!!.removeAuthStateListener(mAuthStateListener)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_sing_out -> {
                AuthUI.getInstance().signOut(this)
                super.onBackPressed()
                true
            }

            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*@OnClick(R.id.imgPhotoProfile)
    fun selectPhoto() {
        val intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RC_FROM_GALLERY)
    }*/
}