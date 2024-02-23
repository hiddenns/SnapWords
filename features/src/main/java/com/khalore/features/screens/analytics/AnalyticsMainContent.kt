package com.khalore.features.screens.analytics

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsScreenMainContent(
    state: AnalyticsViewState,
) {

    Log.d("anal", "AnalyticsScreenMainContent: ${state}")
    Box(
        Modifier
            .padding(vertical = 32.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            state.textToNumberAnalyticsList.forEach {
                key(it.message) {
                    Row {
                        Text(text = it.message)
                        Text(text = " --- ")
                        Text(text = it.count.toString())
                    }
                }
            }
        }

    }
}

