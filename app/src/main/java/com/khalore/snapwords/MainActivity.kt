package com.khalore.snapwords

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.khalore.snapwords.navigation.MyBottomBar
import com.khalore.snapwords.navigation.SetupNavGraph
import com.khalore.snapwords.ui.theme.SnapWordsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContent {
            SnapWordsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppUI()
                }
            }
        }
    }

}

@Composable
fun AppUI() {
    val navController = rememberNavController()
    System.setProperty("skiko.renderApi", "SOFTWARE_FAST")
    var selectedScreen by remember {
        mutableIntStateOf(0)
    }

    val onSelectedScreenChanged: (newSelectedScreen: Int) -> Unit = { newSelectedScreen ->
        selectedScreen = newSelectedScreen
    }

    Scaffold(
        bottomBar = {
            MyBottomBar(
                navController,
                selectedScreen,
                onSelectedScreenChanged = onSelectedScreenChanged
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            SetupNavGraph(
                navController = navController,
                onSelectedScreenChanged = onSelectedScreenChanged
            )
        }
    }
}