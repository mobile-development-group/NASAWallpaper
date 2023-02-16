package com.mdgroup.nasawallpapers.core.di

import com.mdgroup.nasawallpapers.domain.interactors.LicenseInteractor
import com.mdgroup.nasawallpapers.domain.interactors.LicenseInteractorImpl
import org.koin.dsl.module

val licenseModule = module {

    single<LicenseInteractor> { LicenseInteractorImpl() }
}