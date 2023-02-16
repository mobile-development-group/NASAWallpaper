package com.mdgroup.nasawallpapers.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mdgroup.nasawallpapers.domain.interactors.LicenseInteractor
import com.mdgroup.nasawallpapers.domain.models.LicenseModel

class LicensesViewModel(private val interactor: LicenseInteractor) : BaseViewModel() {

    data class State(
        val isLoading: Boolean = true,
        val licenses: List<LicenseModel>
    )

    var state: State by mutableStateOf(
        State(
            isLoading = true,
            licenses = emptyList()
        )
    )

    fun fetch(json: String) {
        state = State(isLoading = false, licenses = interactor.fetch(json))
    }
}