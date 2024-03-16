package com.wizardrrr.appitanes

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(private val context: Context) : InfoWindowAdapter{
    override fun getInfoWindow(marker: Marker): View? {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.modal_map, null)

        val titleTextView = view.findViewById<TextView>(R.id.tvTitleModalMap)
        val imgEstablishment = view.findViewById<ImageView>(R.id.imgEstablishment)
        titleTextView.text = marker.title
        when(marker.title) {
            "Establecimiento 1"-> imgEstablishment.setImageResource(R.drawable.establishment_1)
            "Establecimiento 2"-> imgEstablishment.setImageResource(R.drawable.establishment_2)
            "Establecimiento 3"-> imgEstablishment.setImageResource(R.drawable.establishment_3)
        }

        return view
    }

    override fun getInfoContents(marker: Marker): View? {
        // If you want a custom default info window content
        // Inflate a different layout here and return it
        // Otherwise, return null to use the default behavior
        return null
    }
}