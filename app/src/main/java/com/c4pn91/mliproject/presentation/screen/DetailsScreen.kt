package com.c4pn91.mliproject.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.c4pn91.mliproject.presentation.screen.component.LinearProgress
import com.c4pn91.mliproject.presentation.screen.component.SectionsDetails
import com.c4pn91.mliproject.presentation.state.DetailsResultStateUI
import com.c4pn91.mliproject.presentation.theme.YellowMeli
import com.c4pn91.mliproject.presentation.viewmodel.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    productId: String
) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val detailsState by detailsViewModel.detailsState.collectAsState()

    LaunchedEffect(productId) {
        productId?.let {
            detailsViewModel.loadDetails(productId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = YellowMeli),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (detailsState) {
                is DetailsResultStateUI.Loading, DetailsResultStateUI.Init -> {
                    LinearProgress()
                }
                is DetailsResultStateUI.Success -> {
                    SectionsDetails((detailsState as DetailsResultStateUI.Success).details)
                }
                is DetailsResultStateUI.Empty -> {
                    Text("No hay detalles")
                }
                is DetailsResultStateUI.Error -> {
                    Text((detailsState as DetailsResultStateUI.Error).message)
                }
            }
        }
    }
}

