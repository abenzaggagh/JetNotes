package com.abenzaggagh.jetnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abenzaggagh.jetnote.screens.details.DetailsScreen
import com.abenzaggagh.jetnote.screens.home.HomeScreen
import com.abenzaggagh.jetnote.screens.home.NoteViewModel


@Composable
fun NoteNavigation(viewModel: NoteViewModel) {


    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NoteScreens.HomeScreen.name
    ) {

        composable(NoteScreens.HomeScreen.name) {
            HomeScreen(navController = navController, viewModel)
        }

        composable(
            NoteScreens.DetailsScreen.name + "/{note}",
            arguments = listOf(navArgument(name = "note") { type = NavType.StringType})
        ) {
                backStackEntry ->
            DetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getString("note"),
                viewModel
            )
        }

    }
}