package com.c4pn91.mliproject.common.navigation

sealed class Routes(val route: String) {
    data object SearchScreen: Routes("search_screen")
    data object ProductsScreen: Routes("products_screen")
    data object DetailsScreen: Routes("details_screen")
}