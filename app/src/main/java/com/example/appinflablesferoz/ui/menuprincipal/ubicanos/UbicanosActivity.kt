package com.example.appinflablesferoz.ui.menuprincipal.ubicanos

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.appinflablesferoz.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class UbicanosActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicanos)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val home = LatLng(19.3511535,-99.3345123)

        //mMap.setMinZoomPreference(10);//Sirven para los niveles de zoom
        //mMap.setMaxZoomPreference(15);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);


        //LatLng home = new LatLng(19.351358, -99.332313);
        //
        //
        val home = LatLng(19.3511943, -99.332412)

        mMap.addMarker(MarkerOptions().position(home).title("Contacto"))
        val camera = CameraPosition.Builder()
            .target(home)
            .zoom(15f)
            .bearing(0f)
            .tilt(30f)
            .build()



        mMap.moveCamera(CameraUpdateFactory.newLatLng(home))
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        //Personalizando el mapa
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json
                )
            )
            if (!success) {
                Log.e("", "Style parsing failed.")
            }
        } catch (e: NotFoundException) {
            Log.e("", "Can't find style. Error: ", e)
        }
    }
}