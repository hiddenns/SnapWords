package com.khalore.features.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoonDevelopButton(
    text: String,
    description: String,
    modifier: Modifier
) {
    val openDialog = remember { mutableStateOf(false) }

    FilledTonalButton(
        onClick = {
            openDialog.value = true
        },
        modifier = modifier
    ) {
        Text(text = text)
    }

    if (openDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp,
                    hoveredElevation = 20.dp
                ),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Soon to be developed!",
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Text(
                        text = description,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    FilledTonalButton(
                        onClick = {
                            openDialog.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Okay",
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(8.dp).width(4.dp))
                            Icon(
                                Icons.Filled.Favorite,
                                "",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}