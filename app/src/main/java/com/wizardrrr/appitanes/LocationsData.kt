package com.wizardrrr.appitanes

import com.google.android.gms.maps.model.LatLng

data class LocationsData(val title: String, val description: String, val latLng: LatLng,val srcImage:Int)

val locations = mutableListOf<LocationsData>(
    LocationsData(
        "Hostal Cielo",
        "Visita en la mañana para verificar la seguridad en las instalaciones. Se revisarán las cámaras de vigilancia, los sistemas de alarma y las medidas de control de acceso. También se entrevistará al personal de seguridad para evaluar su conocimiento de los protocolos de emergencia. ",
        LatLng(-12.07216539986152, -77.07015007517734),
        R.drawable.establishment_1
    ),
    LocationsData(
        "Hotel Olaya",
        "Visita en la tarde para realizar una inspección general del local. Se revisará el estado de las instalaciones, el inventario de productos y la atención al cliente.",
        LatLng(-12.068500340426482, -77.07117932022452),
        R.drawable.establishment_2
    ),
    LocationsData(
        "Hotel 3 estrellas Prince",
        " Visita en la noche para realizar un control de calidad del servicio. Se evaluará la limpieza del local, la calidad de la comida y el tiempo de espera de los clientes.",
        LatLng(-12.069583055580594, -77.06851076007389),
        R.drawable.establishment_3
    )
)