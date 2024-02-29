package com.example.navtest

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Layout1") {
        composable(route = "Layout1") {
            layoutOne(navController)
        }
        composable(route = "Layout2") {
            layoutTwo(navController)
        }
        composable(
            route = "Layout3/{montantTTC}",
            arguments = listOf(
                navArgument(name="montantTTC") {
                    type = NavType.StringType
                },
            )
        ) { backstackEntry ->
            layoutThree(navController,
                montantTTC = backstackEntry.arguments?.getString("montantTTC").toString()
                )
        }
    }
}