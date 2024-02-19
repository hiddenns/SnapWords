package com.khalore.features.components.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.WordsCombination
import com.khalore.features.components.AutoResizeText
import com.khalore.features.components.FontSizeRange

class CardViewCardParameter : PreviewParameterProvider<Card> {
    override val values = sequenceOf(
        Card(
            rate = 0,
            wordCombination = WordsCombination(
                wordCombinationId = 1,
                word = "Word super e 34tretr re gerg er g erg erg",
                otherWord = "Translate Translate Translate",
                description = "Translate Translate TranslateTranslate Translate",
                otherDescription = null
            )
        )
    )
}

@Preview
@Composable
fun CardViewCard(
    @PreviewParameter(CardViewCardParameter::class) card: Card,
    onRemove: ((Card) -> Unit)? = null,
    onEdit: ((Card) -> Unit)? = null
) {
    val wordsCombination = card.wordCombination
    Card(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .fillMaxWidth(0.85f)
            ) {
                AutoResizeText(
                    wordsCombination.word,
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    fontSizeRange = FontSizeRange(
                        min = 6.sp,
                        max = 22.sp,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val learnedPercent = card.getProgressPercent()
                    LinearProgressIndicator(
                        progress = learnedPercent.div(100f),
                        modifier = Modifier.fillMaxWidth(0.9f),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        strokeCap = StrokeCap.Round
                    )
                    Text(
                        text = "$learnedPercent%",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    wordsCombination.otherWord,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Column(
                modifier = Modifier
            ) {
                IconButton(
                    onClick = {
                        if (onEdit != null) {
                            onEdit(card)
                        }
                    },
                ) {
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "edit card",
                    )
                }

                IconButton(
                    onClick = {
                        if (onRemove != null) {
                            onRemove(card)
                        }
                    },
                ) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = "delete card"
                    )
                }
            }
        }
    }
}