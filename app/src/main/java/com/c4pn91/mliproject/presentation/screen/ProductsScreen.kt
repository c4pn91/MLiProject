package com.c4pn91.mliproject.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.c4pn91.mliproject.presentation.screen.component.CircularProgress
import com.c4pn91.mliproject.presentation.screen.component.LinearProgress
import com.c4pn91.mliproject.presentation.screen.component.ProductItem
import com.c4pn91.mliproject.presentation.state.ProductsResultStateUI
import com.c4pn91.mliproject.presentation.theme.YellowMeli
import com.c4pn91.mliproject.presentation.theme.searchTextTextStyle
import com.c4pn91.mliproject.presentation.viewmodel.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    navController: NavController,
    searchText: String
) {
    val productsViewModel: ProductsViewModel = hiltViewModel()
    val productsState by productsViewModel.productsState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        searchText.let { productsViewModel.loadProducts(it) }
    }

    Scaffold(
        topBar = {
                TopAppBar(
                    modifier = Modifier.padding(0.dp),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.DarkGray
                            )
                        }
                    },
                    title = {
                        Box(
                            modifier = Modifier
                                .padding(end = 18.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color.White)
                                .clickable { navController.popBackStack() }
                                .fillMaxWidth()
                                .padding(0.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Filled.Search,
                                    tint = Color.Gray,
                                    contentDescription = "Buscar",
                                    modifier = Modifier.padding(end = 0.dp, start = 12.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(start = 12.dp)
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        searchText,
                                        style = searchTextTextStyle
                                    )
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = YellowMeli),
                )
        }
    ) { paddingValue ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)) {

            LazyColumn(state = listState) {
                when (productsState) {
                    is ProductsResultStateUI.Init, ProductsResultStateUI.Loading -> {
                        item { LinearProgress() }
                    }
                    is ProductsResultStateUI.Success -> {
                        val products = (productsState as ProductsResultStateUI.Success).products
                        items(products.size) { index ->
                            val product = products[index]
                            ProductItem(product, navController)
                            HorizontalDivider(Modifier.height(0.5.dp), color = Color.LightGray)
                        }

                        item {
                            if (productsViewModel.hasMoreResults()) { CircularProgress() }
                        }
                    }
                    is ProductsResultStateUI.Empty -> {
                        item {
                            Text("No se encontraron productos.")
                        }
                    }
                    is ProductsResultStateUI.Error -> {
                        val message = (productsState as ProductsResultStateUI.Error).message
                        item {
                            Text("Error: $message")
                        }
                    }
                }
            }

            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                    .collect { lastVisibleItemIndex ->
                        if (lastVisibleItemIndex == productsViewModel.currentProducts.size?.minus(3) && productsViewModel.hasMoreResults()) {
                            productsViewModel.loadProducts(productsViewModel.currentQuery.value ?: "")
                        }
                    }
            }
        }
    }
}
