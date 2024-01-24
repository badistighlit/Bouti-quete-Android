package com.example.myapplication.network



class LocalisationMapper constructor() {

    fun mapping(entity: LocalisationResponseObject): LocalisationData {
        val location = entity.results.firstOrNull()?.geometry?.location

        return LocalisationData(
            latitude = location?.latitude ?: 0.0,
            longitude = location?.longitude ?: 0.0
        )
    }
}
