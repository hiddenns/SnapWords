package com.khalore.snapwords.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khalore.features.screens.collection.CollectionScreen
import com.khalore.features.screens.home.HomeScreen
import com.khalore.features.screens.settings.SettingsScreen
import com.khalore.features.screens.shop.ShopScreen
import com.khalore.snapwords.R

sealed class Screen(val route: String, val name: String, var icon: ImageVector) {
    data object Home : Screen(route = "home_screen", name = "Home", icon = Icons.Default.Home)
    data object Collection : Screen(route = "collection_screen", name = "Collection", icon = Icons.Outlined.Menu)
    data object Shop : Screen(route = "shop_screen", name = "Shop", icon = Icons.Default.ShoppingCart)
    data object Settings : Screen(route = "settings_screen", name = "Settings", icon = Icons.Default.Settings)
}

val navigationItems = listOf(
    Screen.Home,
    Screen.Collection,
    Screen.Shop,
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
                    navController.navigate(Screen.Shop.route)
                    onSelectedScreenChanged(navigationItems.indexOf(Screen.Shop))
                }
            )
        }

        composable(
            route = Screen.Collection.route
        ) {
            CollectionScreen()
        }

        composable(
            route = Screen.Shop.route
        ) {
            ShopScreen()
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
                label = { Text(text = screen.name) },
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
                    id = R.drawable.ic_collection_cards
                ),
                contentDescription = null
            )
        }
        is Screen.Shop -> {
            Icon(
                painterResource(
                    id = R.drawable.ic_shop
                ),
                contentDescription = null
            )
        }
        is Screen.Settings -> {
            Icon(
                painterResource(
                    id = R.drawable.ic_settings
                ),
                contentDescription = null
            )
        }
    }

}