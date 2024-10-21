package com.c4pn91.mliproject.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.mliproject.domain.usecase.GetProductsUseCase
import com.c4pn91.mliproject.presentation.model.ProductResultUI
import com.c4pn91.mliproject.presentation.state.ProductsResultStateUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow<ProductsResultStateUI>(ProductsResultStateUI.Init)
    val productsState: StateFlow<ProductsResultStateUI> = _productsState

    // Mantener la lista de productos actuales
    val currentProducts = mutableStateListOf<ProductResultUI>()

    private val _currentQuery = MutableLiveData<String>()
    val currentQuery: LiveData<String> = _currentQuery

    private var hasMoreResults: Boolean = true

    fun loadProducts(query: String) {
        if (!hasMoreResults || query.isEmpty()) return

        setCurrentQuery(query)

        viewModelScope.launch {
            val result = getProductsUseCase(_currentQuery.value ?: "")
            when (result) {
                is ProductsResultStateUI.Init -> {
                    _productsState.value = result
                }
                is ProductsResultStateUI.Loading -> {
                    _productsState.value = result
                }
                is ProductsResultStateUI.Success -> {
                    currentProducts.addAll(result.products)

                    _productsState.value = ProductsResultStateUI.Success(
                        products = currentProducts,
                        hasMore = result.hasMore
                    )
                    hasMoreResults = result.hasMore
                }
                is ProductsResultStateUI.Empty -> {
                    _productsState.value = result
                }
                is ProductsResultStateUI.Error -> {
                    _productsState.value = result
                }
            }
        }
    }

    fun hasMoreResults(): Boolean {
        return hasMoreResults
    }

    fun setCurrentQuery(text: String) {
        _currentQuery.value = text
    }
}