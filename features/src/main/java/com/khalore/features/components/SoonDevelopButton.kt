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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.khalore.snapwords.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoonDevelopButton(
    text: String,
    description: String,
    modifier: Modifier,
    onClickSoonButton: () -> Unit,
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
                onClickSoonButton()
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
                        text = stringResource(id = R.string.soon_be_develop),
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = description,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.bodyLarge
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
                                text = stringResource(id = R.string.okay),
                                textAlign = TextAlign.Center
                            )
                            Spacer(
                                Modifier
                                    .height(8.dp)
                                    .width(4.dp))
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