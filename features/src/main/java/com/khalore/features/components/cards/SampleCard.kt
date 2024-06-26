package com.khalore.features.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khalore.core.model.word.Word
import com.khalore.core.model.word.WordsCombination
import com.khalore.core.model.word.getWord
import com.khalore.features.components.AutoResizeText
import com.khalore.features.components.FontSizeRange

class WordSampleProvider : PreviewParameterProvider<Word> {
    override val values = sequenceOf(
        WordsCombination(
            wordCombinationId = 1,
            word = "Word super long word long",
            otherWord = "Translate Translate Translate",
            description = "Translate Translate TranslateTranslate Translate",
            otherDescription = null
        ).getWord()
    )
}

@Preview(
    widthDp = 340
)
@Composable
fun SampleCard(
    @PreviewParameter(WordSampleProvider::class) word: Word,
    backgroundColor: Color = Color.White
) {
    Card(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth(.8f),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AutoResizeText(
                    text = word.word,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSizeRange = FontSizeRange(
                        min = 10.sp,
                        max = 22.sp,
                    ),
                    overflow = TextOverflow.Ellipsis,
                )
            }

            word.description?.let { wordDescription ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AutoResizeText(
                        text = wordDescription,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSizeRange = FontSizeRange(
                            min = 6.sp,
                            max = 22.sp,
                        ),
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}