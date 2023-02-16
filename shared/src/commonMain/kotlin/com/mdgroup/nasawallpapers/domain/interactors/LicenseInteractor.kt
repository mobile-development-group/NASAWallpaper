package com.mdgroup.nasawallpapers.domain.interactors

import com.mdgroup.nasawallpapers.domain.models.LicenseModel

interface LicenseInteractor {

    fun fetch(json: String): List<LicenseModel>

}