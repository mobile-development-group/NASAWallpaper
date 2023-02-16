package com.mdgroup.nasawallpapers.domain.interactors

import com.mdgroup.nasawallpapers.domain.models.LicenseModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LicenseInteractorImpl : LicenseInteractor {

    override fun fetch(json: String): List<LicenseModel> {
        return Json.decodeFromString(json)
    }

}