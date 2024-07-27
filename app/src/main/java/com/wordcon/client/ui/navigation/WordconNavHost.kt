package com.wordcon.client.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wordcon.client.ui.screens.GamesScreen
import com.wordcon.client.ui.screens.HomeScreen
import com.wordcon.client.ui.screens.MainScreen
import com.wordcon.client.ui.screens.AccountScreen
import com.wordcon.client.ui.screens.RatingScreen

@Composable
fun WordconNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen()
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(modifier = modifier)
        }
        composable(Screen.GamesScreen.route) {
            GamesScreen()
        }
        composable(Screen.RatingScreen.route) {
            RatingScreen()
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen()
        }
    }
}