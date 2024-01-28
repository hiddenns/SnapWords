package com.khalore.features.screens.collection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.khalore.core.base.State
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.Word
import com.khalore.core.model.word.WordsCombination
import com.khalore.features.components.Error
import com.khalore.features.components.cardlist.CollectionList
import com.khalore.features.components.cards.CardFace
import com.khalore.features.components.cards.FlipCard
import com.khalore.features.components.cards.RotationAxis
import com.khalore.features.components.cards.SmallSampleCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionCardsDialog(
    onSaveCard: (card: Card) -> Unit,
    viewState: State<CollectionViewState>,
    onRemoveCard: (Card) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Create Card") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->

        when (viewState) {
            is State.Data -> CollectionList(
                cardList = viewState.asData().cardsList,
                onRemoveCard = onRemoveCard
            )

            is State.Error -> Error()
            is State.Loading -> Error()
            is State.None -> EmptyCollection()
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize(),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,

                ) {

                var wordText by remember {
                    mutableStateOf("")
                }

                var translateText by remember {
                    mutableStateOf("")
                }

                var descriptionText by remember {
                    mutableStateOf("")
                }

                var translateDescriptionText by remember {
                    mutableStateOf("")
                }

                var cardFace by remember {
                    mutableStateOf(CardFace.Front)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        FlipCard(
                            cardFace = cardFace,
                            modifier = Modifier
                                .padding(horizontal = 52.dp),
                            onClick = {
                                cardFace = cardFace.next
                            },
                            onMoveToBack = {},
                            axis = RotationAxis.AxisY,
                            front = {
                                SmallSampleCard(
                                    backgroundColor = Color.Unspecified,
                                    word = Word(
                                        word = wordText.takeIf { it.isNotBlank() } ?: "Write word",
                                        description = descriptionText.takeIf { it.isNotBlank() }
                                    ),
                                )
                            },
                            back = {
                                SmallSampleCard(
                                    backgroundColor = Color.Magenta,
                                    word = Word(
                                        word = translateText.takeIf { it.isNotBlank() }
                                            ?: "Write translate",
                                        description = translateDescriptionText.takeIf { it.isNotBlank() }
                                    ),
                                )
                            },
                            isSwappable = false
                        )

                        OutlinedTextField(
                            value = wordText,
                            onValueChange = {
                                cardFace = CardFace.Front
                                wordText = it
                            },
                            label = { Text("Word") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            isError = wordText.isBlank(), // Example error condition (can be adjusted)
                            colors = if (wordText.isBlank()) OutlinedTextFieldDefaults.colors(
                                errorCursorColor = Color.Red,
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red,
                                errorLeadingIconColor = Color.Red,
                                errorTrailingIconColor = Color.Red,
                            ) else OutlinedTextFieldDefaults.colors(
                            ),
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = descriptionText,
                            onValueChange = {
                                cardFace = CardFace.Front
                                descriptionText = it
                            },
                            label = { Text("Word description") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            colors = OutlinedTextFieldDefaults.colors(),
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        )

                        Divider(
                            modifier = Modifier
                                .padding(
                                    horizontal = 32.dp,
                                    vertical = 16.dp
                                )
                                .fillMaxWidth()
                                .height(1.dp)
                        )


                        OutlinedTextField(
                            value = translateText,
                            onValueChange = {
                                cardFace = CardFace.Back
                                translateText = it
                            },
                            label = { Text("Translate") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            isError = translateText.isBlank(), // Example error condition (can be adjusted)
                            colors = if (translateText.isBlank()) OutlinedTextFieldDefaults.colors(
                                errorCursorColor = Color.Red,
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Red,
                                errorLeadingIconColor = Color.Red,
                                errorTrailingIconColor = Color.Red,
                            ) else OutlinedTextFieldDefaults.colors(),
                            modifier = Modifier
                                .padding(top = 0.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = translateDescriptionText,
                            onValueChange = {
                                cardFace = CardFace.Back
                                translateDescriptionText = it
                            },
                            label = { Text("Translate description") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            colors = OutlinedTextFieldDefaults.colors(),
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                    onSaveCard(
                                        Card(
                                            wordCombination = WordsCombination(
                                                word = wordText,
                                                translate = translateText,
                                                description = descriptionText,
                                                translateDescription = translateDescriptionText
                                            ),
                                            rate = 0
                                        )
                                    )
                                }
                            }) {
                            Text("Save card")
                        }

                    }
                }
            }
        }

    }
}