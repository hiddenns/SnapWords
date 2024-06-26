package com.khalore.features.screens.collection

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.khalore.core.base.State
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.Word
import com.khalore.core.model.word.WordsCombination
import com.khalore.core.model.word.getOtherWord
import com.khalore.core.model.word.getWord
import com.khalore.features.components.Error
import com.khalore.features.components.LoadingScreen
import com.khalore.features.components.cardlist.CollectionList
import com.khalore.features.components.cards.AnimatedCard
import com.khalore.features.components.cards.CardFace
import com.khalore.features.components.cards.FlipCard
import com.khalore.features.components.cards.RotationAxis
import com.khalore.features.components.cards.SmallSampleCard
import com.khalore.features.components.cards.cardsColors
import com.khalore.snapwords.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionCardsDialog(
    onSaveCard: (card: Card) -> Unit,
    onUpdateCard: (card: Card) -> Unit,
    viewState: State<CollectionViewState>,
    onRemoveCard: (Card) -> Unit,
    onAddDefaults: () -> Unit,
    onAddClickFloatButton: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val editCard: MutableState<Card?> = remember { mutableStateOf(null) }

    val onEditCard = { card: Card ->
        editCard.value = card
        showBottomSheet = true
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(stringResource(id = R.string.create_card)) },
                icon = { Icon(Icons.Rounded.Add, contentDescription = "") },
                onClick = {
                    onAddClickFloatButton()
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->

        when (viewState) {
            is State.Data -> CollectionList(
                cardList = viewState.asData().cardsList,
                onRemoveCard = onRemoveCard,
                onEditCard = onEditCard
            )

            is State.Error -> Error()
            is State.Loading -> LoadingScreen()
            is State.None -> EmptyCollection(
                onAddDefaults = onAddDefaults
            )
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

                val focusRequester = remember { FocusRequester() }

                val randomWordCardColor by remember {
                    mutableStateOf(cardsColors.random())
                }

                val randomTranslateCardColor by remember {
                    mutableStateOf(cardsColors.filterNot { it == randomWordCardColor }.random())
                }

                val wordEditCombination = remember {
                    mutableStateOf(editCard.value?.wordCombination)
                }

                var wordText by remember {
                    mutableStateOf(wordEditCombination.value?.getWord()?.word ?: "")
                }

                var otherText by remember {
                    mutableStateOf(wordEditCombination.value?.getOtherWord()?.word ?: "")
                }

                var descriptionText by remember {
                    mutableStateOf(wordEditCombination.value?.getWord()?.description ?: "")
                }

                var otherDescriptionText by remember {
                    mutableStateOf(wordEditCombination.value?.getOtherWord()?.description ?: "")
                }

                var animatedCard by remember {
                    mutableStateOf(
                        AnimatedCard(
                            cardFace = CardFace.Front,
                            card = Card(
                                wordCombination = WordsCombination(
                                    word = wordText,
                                    otherWord = otherText,
                                    description = descriptionText,
                                    otherDescription = otherDescriptionText
                                ),
                                rate = 0
                            )
                        )
                    )
                }

                var isValidInputs by remember {
                    mutableStateOf(wordText.isNotBlank() && otherText.isNotBlank())
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        FlipCard(
                            animatedCard = animatedCard,
                            onClick = {
                                animatedCard = animatedCard.next()
                            },
                            modifier = Modifier
                                .padding(horizontal = 52.dp),
                            axis = RotationAxis.AxisY,
                            back = {
                                SmallSampleCard(
                                    backgroundColor = randomTranslateCardColor,
                                    word = Word(
                                        word = otherText.takeIf { it.isNotBlank() }
                                            ?: stringResource(id = R.string.another_word),
                                        description = otherDescriptionText.takeIf { it.isNotBlank() }
                                    ),
                                )
                            },
                            front = {
                                SmallSampleCard(
                                    backgroundColor = randomWordCardColor,
                                    word = Word(
                                        word = wordText.takeIf { it.isNotBlank() }
                                            ?: stringResource(id = R.string.write_word),
                                        description = descriptionText.takeIf { it.isNotBlank() }
                                    ),
                                )
                            },
                            onMoveToBack = {},
                            isSwappable = false,
                            onEndAnimation = {}
                        )

                        FilledTonalButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            enabled = isValidInputs,
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }

                                    if (editCard.value == null) {
                                        onSaveCard(
                                            Card(
                                                wordCombination = WordsCombination(
                                                    word = wordText,
                                                    otherWord = otherText,
                                                    description = descriptionText,
                                                    otherDescription = otherDescriptionText
                                                ),
                                                rate = 0
                                            )
                                        )
                                    } else {
                                        editCard.value?.let {
                                            onUpdateCard(
                                                Card(
                                                    cardId = it.cardId,
                                                    wordCombination = WordsCombination(
                                                        wordCombinationId = it.wordCombination.wordCombinationId,
                                                        word = wordText,
                                                        otherWord = otherText,
                                                        description = descriptionText,
                                                        otherDescription = otherDescriptionText
                                                    ),
                                                    rate = 0
                                                )
                                            )
                                        }
                                    }
                                    editCard.value = null
                                    wordText = ""
                                    descriptionText = ""
                                    otherText = ""
                                    otherDescriptionText = ""
                                }
                            }) {
                            Text(stringResource(id = R.string.save_card))
                        }

                        OutlinedTextField(
                            value = wordText,
                            onValueChange = {
                                animatedCard = animatedCard.copy(cardFace = CardFace.Front)
                                wordText = it
                                isValidInputs = wordText.isNotBlank() && otherText.isNotBlank()
                            },
                            label = { Text(stringResource(id = R.string.word)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            isError = wordText.isBlank(),
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
                                .focusRequester(focusRequester),
                        )

                        OutlinedTextField(
                            value = descriptionText,
                            onValueChange = {
                                animatedCard = animatedCard.copy(cardFace = CardFace.Front)
                                descriptionText = it
                            },
                            label = { Text(stringResource(id = R.string.word_description)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            colors = OutlinedTextFieldDefaults.colors(),
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        )

                        HorizontalDivider(
                            modifier = Modifier
                                .padding(
                                    horizontal = 32.dp,
                                    vertical = 16.dp
                                )
                                .fillMaxWidth()
                                .height(1.dp)
                        )


                        OutlinedTextField(
                            value = otherText,
                            onValueChange = {
                                animatedCard = animatedCard.copy(cardFace = CardFace.Back)
                                otherText = it
                                isValidInputs = wordText.isNotBlank() && otherText.isNotBlank()
                            },
                            label = { Text(stringResource(id = R.string.another_word)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            isError = otherText.isBlank(),
                            colors = if (otherText.isBlank()) OutlinedTextFieldDefaults.colors(
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
                            value = otherDescriptionText,
                            onValueChange = {
                                animatedCard = animatedCard.copy(cardFace = CardFace.Back)
                                otherDescriptionText = it
                            },
                            label = { Text(stringResource(id = R.string.description)) },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            visualTransformation = VisualTransformation.None,
                            colors = OutlinedTextFieldDefaults.colors(),
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                        )

                    }
                }

                val windowInfo = LocalWindowInfo.current

                LaunchedEffect(windowInfo) {
                    snapshotFlow { windowInfo.isWindowFocused }.collect { isWindowFocused ->
                        if (isWindowFocused) {
                            focusRequester.requestFocus()
                        }
                    }
                }
            }
        }

    }
}