package com.example.appinflablesferoz

import android.content.res.Resources
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class BuscarDireccionMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var direccion:String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_direccion_maps)

        val message = intent.getStringExtra("EXTRA_DIRECCION")
        Log.e("","Direccion" + message)
        direccion = message

        /*val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }*/
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

        val geo = Geocoder(this)
        val maxResultados = 1
        val adress: List<Address> =
            geo.getFromLocationName(direccion, maxResultados)
        val latLng = LatLng(adress[0].getLatitude(), adress[0].getLongitude())

        Log.e("Geoloco","buscarzaca::" + latLng)


        //val home = LatLng(19.3511943, -99.332412)

        mMap.addMarker(MarkerOptions().position(latLng).title("Mi direccion"))
        val camera = CameraPosition.Builder()
            .target(latLng)
            .zoom(15f)
            .bearing(0f)
            .tilt(30f)
            .build()



        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
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
        } catch (e: Resources.NotFoundException) {
            Log.e("", "Can't find style. Error: ", e)
        }

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(latLng.latitude,latLng.longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }
}