package com.mdgroup.nasawallpapers.core.di

import com.mdgroup.nasawallpapers.data.network.ApiInterface
import com.mdgroup.nasawallpapers.data.repositories.NasaRepositoryImpl
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractor
import com.mdgroup.nasawallpapers.domain.interactors.NasaInteractorImpl
import com.mdgroup.nasawallpapers.domain.repositories.NasaRepository
import org.koin.dsl.module

val nasaModule = module {

    single<NasaRepository> { NasaRepositoryImpl(get<ApiInterface>()) }

    single<NasaInteractor> { NasaInteractorImpl(get<NasaRepository>()) }
}