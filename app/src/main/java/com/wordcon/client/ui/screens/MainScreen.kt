package com.wordcon.client.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wordcon.client.R
import com.wordcon.client.ui.navigation.BottomNavItem
import com.wordcon.client.ui.navigation.Screen
import com.wordcon.client.ui.navigation.WordconNavBar
import com.wordcon.client.ui.navigation.WordconNavHost

@Composable
fun MainScreen() {
    val topLevelDestinations = listOf(
        BottomNavItem(
            route = Screen.HomeScreen.route,
            labelRes = R.string.home,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            route = Screen.GamesScreen.route,
            labelRes = R.string.games,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.gamepad_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.gamepad_outlined)
        ),
        BottomNavItem(
            route = Screen.RatingScreen.route,
            labelRes = R.string.rating,
            selectedIcon = ImageVector.vectorResource(id = R.drawable.trophy_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.trophy_outlined)
        ),
        BottomNavItem(
            route = Screen.AccountScreen.route,
            labelRes = R.string.account,
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
        )
    )

    val localNavController = rememberNavController()
    val currentRoute = localNavController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in topLevelDestinations.map { it.route }) {
                WordconNavBar(
                    navController = localNavController,
                    destinations = topLevelDestinations
                )
            }
        },
        contentWindowInsets = WindowInsets(top = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            WordconNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                navController = localNavController
            )
        }
    }
}