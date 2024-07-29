package com.wordcon.client.ui.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")

    data object HomeScreen : Screen("home_screen")
    data object GamesScreen : Screen("games_screen")
    data object RatingScreen : Screen("rating_screen")
    data object AccountScreen : Screen("account_screen")
}