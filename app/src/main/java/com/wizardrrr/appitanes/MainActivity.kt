package com.wizardrrr.appitanes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.wizardrrr.appitanes.BuildConfig.OPEN_ROUTE_SERVICE_API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var tvAppTitle: TextView
    private lateinit var tvAppDescription: TextView
    private var centerMap: LatLng = LatLng(-12.125717562216492, -77.03234292655931)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onInitComponents()
    }

    private fun onInitComponents() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        tvAppTitle = findViewById(R.id.tvAppTitle)
        tvAppDescription = findViewById(R.id.tvAppDescription)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        // add markers
        locations.forEach { location ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(location.latLng)
                    .title(location.title)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            )
        }

        // animation camera
        val cameraPosition = CameraPosition.Builder()
            .target(centerMap)
            .zoom(17f)
            .bearing(0f)
            .tilt(5f)
            .build()

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // event click markers
        googleMap.setOnMarkerClickListener { marker ->
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 17f))
            googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(this))
            marker.showInfoWindow()

            locations.forEach { location ->
                if (location.title == marker.title) {
                    tvAppTitle.text = location.title
                    tvAppDescription.text = location.description
                }
            }
            true
        }

        // event click map
        googleMap.setOnMapClickListener {
            tvAppTitle.text = "Itanes App"
            tvAppDescription.text = "By WizardRRR"
        }

        // controls for map
        googleMap.uiSettings.isZoomControlsEnabled = true

        // trazando las rutas
        createRoute(googleMap, locations[0].latLng, locations[2].latLng, Color.YELLOW)
        createRoute(googleMap, locations[0].latLng, locations[4].latLng, Color.RED)
        createRoute(googleMap, locations[4].latLng, locations[3].latLng, Color.GREEN)
        createRoute(googleMap, locations[3].latLng, locations[1].latLng, Color.BLACK)
        createRoute(googleMap, locations[1].latLng, locations[2].latLng, Color.CYAN)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createRoute(googleMap: GoogleMap, start: LatLng, end: LatLng, color: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(ApiService::class.java)
                .getRoute(
                    OPEN_ROUTE_SERVICE_API_KEY,
                    "${start.longitude},${start.latitude}",
                    "${end.longitude},${end.latitude}"
                )

            if (call.isSuccessful) {
                drawRoute(googleMap, call.body(), color)
            } else {
                Log.ERROR
            }
        }
    }

    private fun drawRoute(googleMap: GoogleMap, routeResponse: RouteResponse?, color: Int) {
        val polylineOption = PolylineOptions().color(color).width(10f)
        routeResponse?.features?.first()?.geometry?.coordinates?.forEach {
            polylineOption.add(LatLng(it[1], it[0]))
        }
        runOnUiThread {
            val poly = googleMap.addPolyline(polylineOption)
        }
    }
}