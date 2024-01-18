package com.khalore.features.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Error() {
    Text(
        text = "Something wrong.",
        modifier = Modifier
            .padding(16.dp),
        textAlign = TextAlign.Center,
    )
}