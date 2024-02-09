package com.example.myapplication.network

import android.text.Editable
import com.example.myapplication.model.magasin_model.Adresse


class LocalisationMapper constructor() {

    fun mapping(entity: LocalisationResponseObject): LocalisationData {
        val location = entity.results.firstOrNull()?.geometry?.location

        return LocalisationData(
            latitude = location?.latitude ?: 0.0,
            longitude = location?.longitude ?: 0.0
        )
    }

    fun mappingAdress(entity: LocalisationResponseObjectAdress): Adresse {
        val position = entity.results.firstOrNull()?.position ?: ""
        return parseAddress(position)
    }
    fun parseAddress(addressString: String): Adresse {
        var a =Adresse(9999,"abcd","abcd","abcd",0.0,0.0)
        val parts = addressString.split(",")

        if (parts.size >= 3) {
            val street = parts[0].trim()

            val city = parts[1].trim()

            val postalCode = parts[2].trim()

            a= Adresse(0,street,postalCode,city,0.0,0.0);
        }
        return a;

    }
}