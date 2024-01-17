package com.khalore.snapwords.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khalore.features.screens.editor.EditorScreen
import com.khalore.features.screens.home.HomeScreen
import com.khalore.features.screens.settings.SettingsScreen
import com.khalore.features.screens.shop.ShopScreen

sealed class Screen(val route: String, val name: String, val icon: ImageVector) {
    data object Home: Screen(route = "home_screen", name = "Home", icon = Icons.Default.Home)
    data object Editor: Screen(route = "editor_screen", name = "Editor", icon = Icons.Default.Create)
    data object Shop: Screen(route = "shop_screen", name = "Shop", icon = Icons.Default.ShoppingCart)
    data object Settings: Screen(route = "settings_screen", name = "Settings", icon = Icons.Default.Settings)
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }

        composable(
            route = Screen.Editor.route
        ) {
            EditorScreen()
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
fun MyBottomBar(navController: NavHostController) {
    val navigationItems = listOf(
        Screen.Home,
        Screen.Editor,
        Screen.Shop,
        Screen.Settings
    )

    var selectedScreen by remember {
        mutableIntStateOf(0) // or use mutableStateOf(0)
    }

    NavigationBar {
        navigationItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(text = screen.name) },
                selected = (selectedScreen == index),
                onClick = {
                    // this if condition keeps only one screen in the back stack
                    if (navController.currentBackStack.value.size >= 2) {
                        navController.popBackStack()
                    }
                    selectedScreen = index
                    navController.navigate(screen.route)
                }
            )
        }
    }
}