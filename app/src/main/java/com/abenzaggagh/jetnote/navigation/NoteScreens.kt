package com.abenzaggagh.jetnote.navigation

enum class NoteScreens {
    HomeScreen,
    DetailsScreen,
    EditScreen;

    companion object {
        fun fromRoute(route: String?): NoteScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            EditScreen.name -> EditScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not referred")
        }
    }

}