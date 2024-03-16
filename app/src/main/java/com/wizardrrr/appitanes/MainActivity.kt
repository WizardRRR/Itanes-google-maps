package com.wizardrrr.appitanes

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

data class Location(val title: String, val latLng: LatLng)
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var centerMap: LatLng = LatLng(-12.070346982810078, -77.07014311390193)
    private var locations = mutableListOf<Location>(
        Location("Establecimiento 1", LatLng(-12.07216539986152, -77.07015007517734)),
        Location("Establecimiento 2", LatLng(-12.068500340426482, -77.07117932022452)),
        Location("Establecimiento 3", LatLng(-12.069583055580594, -77.06851076007389))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        locations.forEach { location ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(location.latLng)
                    .title(location.title)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            )
        }

        val cameraPosition = CameraPosition.Builder()
            .target(centerMap)
            .zoom(17f)
            .bearing(0f)
            .tilt(5f)
            .build()

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        googleMap.setOnMarkerClickListener { marker ->
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 17f))
            googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))
            marker.showInfoWindow()
            Log.i("marker", "${marker.title}")
            true
        }

        googleMap.uiSettings.isZoomControlsEnabled = true
    }

}