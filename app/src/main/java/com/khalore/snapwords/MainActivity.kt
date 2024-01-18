package com.khalore.snapwords

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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

//    override fun attachBaseContext(newBase: Context?) {
//        val newOverride = Configuration(newBase?.resources?.configuration)
//        if (newOverride.fontScale >= 1.1f)
//            newOverride.fontScale = 0.9f
//        applyOverrideConfiguration(newOverride)
//        super.attachBaseContext(newBase)
//    }
}

@Composable
fun AppUI() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MyBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            SetupNavGraph(navController = navController)
        }
    }
}