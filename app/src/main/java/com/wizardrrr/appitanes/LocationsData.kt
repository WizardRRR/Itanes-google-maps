package com.wizardrrr.appitanes

import com.google.android.gms.maps.model.LatLng

data class LocationsData(
    val title: String,
    val description: String,
    val latLng: LatLng,
    val srcImage: Int
)

val locations = mutableListOf<LocationsData>(
    LocationsData(
        "Hotel José Antonio Lima",
        "Visita en la mañana para verificar la seguridad en las instalaciones. Se revisarán las cámaras de vigilancia, los sistemas de alarma y las medidas de control de acceso. También se entrevistará al personal de seguridad para evaluar su conocimiento de los protocolos de emergencia. ",
        LatLng(-12.1261, -77.03132),
        R.drawable.establishment_hotel_jose
    ),
    LocationsData(
        "Radisson Hotel Decapolis Miraflores",
        "Visita en la tarde para realizar una inspección general del local. Se revisará el estado de las instalaciones, el inventario de productos y la atención al cliente.",
        LatLng(-12.1246, -77.03347),
        R.drawable.establishment_radisson
    ),
    LocationsData(
        "Iberostar Selection Miraflores",
        " Visita en la noche para realizar un control de calidad del servicio. Se evaluará la limpieza del local, la calidad de la comida y el tiempo de espera de los clientes.",
        LatLng(-12.1257, -77.03348),
        R.drawable.establishment_hotel_iberostar
    ),
    LocationsData(
        "Hotel Solís Dies",
        " Visita en la noche para realizar un control de calidad del servicio. Se evaluará la limpieza del local, la calidad de la comida y el tiempo de espera de los clientes.",
        LatLng(-12.124, -77.03152),
        R.drawable.establishment_solisdies
    ),
    LocationsData(
        "Hotel Equo",
        " Visita en la noche para realizar un control de calidad del servicio. Se evaluará la limpieza del local, la calidad de la comida y el tiempo de espera de los clientes.",
        LatLng(-12.125, -77.03145),
        R.drawable.establishment_hotel_equo
    )
)