package com.khalore.features.components.cardlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.khalore.core.model.word.WordsCombination
import com.khalore.features.components.CardShapeCanvas

@Composable
fun CardViewCard(wordsCombination: WordsCombination) {
    ListItem(
        modifier = Modifier.clip(MaterialTheme.shapes.small),
        headlineContent = {
            Row {
                Text(
                    wordsCombination.word,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.width(8.dp)) // Adjust the width as needed

                Text(
                    "-",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.width(4.dp) // Adjust the width as needed
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    wordsCombination.translate,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        supportingContent = {
            Row {
                wordsCombination.description?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // Adjust the width as needed

                    Text(
                        "-",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.width(4.dp) // Adjust the width as needed
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }

                wordsCombination.translateDescription?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        },
        leadingContent = {
//            Icon(
//                Icons.Default.Star,
//                contentDescription = "person icon",
//                Modifier
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colorScheme.primaryContainer)
//                    .padding(10.dp)
//            )

            CardShapeCanvas()
        }
    )
}