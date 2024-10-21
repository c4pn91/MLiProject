package com.c4pn91.mliproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.mliproject.domain.usecase.GetDetailsUseCase
import com.c4pn91.mliproject.presentation.state.DetailsResultStateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsResultStateUI>(DetailsResultStateUI.Init)
    val detailsState: StateFlow<DetailsResultStateUI> = _detailsState

    fun loadDetails(productId: String?) {
        viewModelScope.launch {
            if (!productId.isNullOrEmpty()) {
                val result = getDetailsUseCase(productId)
                _detailsState.value = result
            } else {
                _detailsState.value = DetailsResultStateUI.Error("Product ID inválido")
            }
        }
    }
}