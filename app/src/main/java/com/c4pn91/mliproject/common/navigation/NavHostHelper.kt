package com.c4pn91.mliproject.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.c4pn91.mliproject.presentation.screen.DetailsScreen
import com.c4pn91.mliproject.presentation.screen.ProductsScreen
import com.c4pn91.mliproject.presentation.screen.SearchScreen

@Composable
fun NavHostHelper(navHostController: NavHostController, startDestination: String) {

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Routes.SearchScreen.route) {
            SearchScreen(navHostController)
        }
        composable("${Routes.ProductsScreen.route}/{query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            ProductsScreen(navHostController, query)
        }
        composable("${Routes.DetailsScreen.route}/{product_id}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("product_id") ?: ""
            DetailsScreen(navHostController, productId)
        }
    }

}