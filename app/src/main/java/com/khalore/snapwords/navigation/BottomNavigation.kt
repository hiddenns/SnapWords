package com.khalore.snapwords.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khalore.features.screens.analytics.AnalyticsScreen
import com.khalore.features.screens.collection.CollectionScreen
import com.khalore.features.screens.home.HomeScreen
import com.khalore.features.screens.settings.SettingsScreen
import com.khalore.snapwords.R

sealed class Screen(val route: String, @StringRes val name: Int) {
    data object Home : Screen(route = "home_screen", name = R.string.home)
    data object Collection : Screen(route = "collection_screen", name = R.string.collection)
    data object Analytics : Screen(route = "analytics_screen", name = R.string.analytics)
    data object Settings : Screen(route = "settings_screen", name = R.string.info)
}

val navigationItems = listOf(
    Screen.Home,
    Screen.Collection,
    Screen.Analytics,
    Screen.Settings
)

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    onSelectedScreenChanged: (newSelectedScreen: Int) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onClickCollection = {
                    navController.navigate(Screen.Collection.route)
                    onSelectedScreenChanged(navigationItems.indexOf(Screen.Collection))
                },
                onClickShop = {
                    navController.navigate(Screen.Analytics.route)
                    onSelectedScreenChanged(navigationItems.indexOf(Screen.Analytics))
                },
                onClickInfo = {
                    navController.navigate(Screen.Settings.route)
                    onSelectedScreenChanged(navigationItems.indexOf(Screen.Settings))
                }
            )
        }

        composable(
            route = Screen.Collection.route
        ) {
            CollectionScreen()
        }

        composable(
            route = Screen.Analytics.route
        ) {
            AnalyticsScreen()
        }

        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen()
        }
    }
}

@Composable
fun MyBottomBar(
    navController: NavHostController,
    selectedScreen: Int,
    onSelectedScreenChanged: (value: Int) -> Unit
) {

    NavigationBar {
        navigationItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { NavigationBarItemIcon(screen) },
                label = { Text(text = stringResource(id = screen.name)) },
                selected = (selectedScreen == index),
                onClick = {
                    // this if condition keeps only one screen in the back stack
                    if (navController.currentBackStack.value.size >= 2) {
                        navController.popBackStack()
                    }
                    onSelectedScreenChanged(index)
                    navController.navigate(screen.route)
                }
            )
        }
    }
}

@Composable
fun NavigationBarItemIcon(screen: Screen) {
    when(screen) {
        is Screen.Home -> {
            Icon(
                painterResource(
                    id = R.drawable.ic_home
                ),
                contentDescription = null
            )
        }
        is Screen.Collection -> {
            Icon(
                painterResource(
                    id = R.drawable.ic_box
                ),
                contentDescription = null
            )
        }
        is Screen.Analytics -> {
            Icon(
                painterResource(
                    id = R.drawable.ic_column_chart
                ),
                contentDescription = null
            )
        }
        is Screen.Settings -> {
            Icon(
                painterResource(
                    id = R.drawable.ic_idea
                ),
                contentDescription = null
            )
        }
    }

}