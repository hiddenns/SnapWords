package com.khalore.features.components.cards


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.khalore.core.model.card.Card
import com.khalore.core.model.word.getTranslate
import com.khalore.core.model.word.getWord
import com.khalore.domain.toShiftList
import com.khalore.features.screens.home.HomeViewState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun SwappableCards(state: HomeViewState) {

    val cardList by remember {
        mutableStateOf(state.cardsList.toShiftList())
    }

    var colors by remember {
        mutableStateOf(state.cardsColors)
    }

    val backgroundColor = Color(0xff141414)

    var gradientColor by remember {
        mutableStateOf(
            Brush.linearGradient(
                colors = listOf(backgroundColor, backgroundColor)
            )
        )
    }

    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val screenHeightPx = with(LocalDensity.current) { screenHeightDp.toPx() }

    val maxOffset = 500.0f
    val maxCoefficient = 0.5f

    val onChangeCardOffsetY = { offsetY: Animatable<Float, AnimationVector1D> ->
        val trueOffset = offsetY.value
        val color = if (trueOffset > 0) Color.Red else Color.Green
        val offset = abs(offsetY.value)

        val coefficient = maxCoefficient * (1 - offset / maxOffset)

        gradientColor = Brush.verticalGradient(
            coefficient to backgroundColor,
            1.0f to color,
            startY = screenHeightPx / 3,
            endY = screenHeightPx + screenHeightPx
        )
    }

    Box(
        Modifier
            .background(brush = gradientColor)
            .padding(vertical = 32.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        colors.forEachIndexed { idx, color ->
            if (idx >= cardList.size()) {
                return
            }
            key(color) {
                SwappableCard(
                    card = cardList[idx],
                    order = idx,
                    totalCount = colors.size,
                    backgroundColor = color,
                    onChangeCardOffsetY = onChangeCardOffsetY,
                    onMoveToBack = {
                        cardList.rotateList()
                        colors = listOf(color) + (colors - color)
                    }
                )
            }
        }
    }
}

@Composable
fun SwappableCard(
    card: Card,
    order: Int,
    totalCount: Int,
    backgroundColor: Color = Color.White,
    onMoveToBack: () -> Unit,
    onChangeCardOffsetY: (Animatable<Float, AnimationVector1D>) -> Unit
) {
    val animatedScale by animateFloatAsState(
        targetValue = 1f - (totalCount - order) * 0.05f, label = "",
    )
    val animatedYOffset by animateDpAsState(
        targetValue = ((totalCount - order) * -12).dp, label = "",
    )
    var cardFace by remember {
        mutableStateOf(CardFace.Front)
    }

    FlipCard(
        cardFace = cardFace,
        modifier = Modifier
            .offset { IntOffset(x = 0, y = animatedYOffset.roundToPx()) }
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            },
        onClick = {
            cardFace = cardFace.next
        },
        onMoveToBack = onMoveToBack,
        axis = RotationAxis.AxisY,
        back = {
            SampleCard(
                backgroundColor = backgroundColor,
                word = card.wordCombination.getTranslate(),
            )
        },
        front = {
            SampleCard(
                backgroundColor = backgroundColor,
                word = card.wordCombination.getWord(),
            )
        },
        isSwappable = true,
        onChangeCardOffsetY = onChangeCardOffsetY
    )
}

fun Modifier.swipeToBack(
    onChangeCardOffsetY: (Animatable<Float, AnimationVector1D>) -> Unit,
    onMoveToBack: () -> Unit
): Modifier = composed {
    val offsetY = remember { Animatable(0f) }
    onChangeCardOffsetY(offsetY)

    val rotation = remember { Animatable(0f) }
    var leftSide by remember { mutableStateOf(true) }
    var clearedHurdle by remember { mutableStateOf(false) }
    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            while (true) {
                offsetY.stop()
                val velocityTracker = VelocityTracker()
                awaitPointerEventScope {
                    verticalDrag(awaitFirstDown().id) { change ->
                        val verticalDragOffset = offsetY.value + change.positionChange().y
                        val horizontalPosition = change.previousPosition.x
                        leftSide = horizontalPosition <= size.width / 2
                        val offsetXRatioFromMiddle = if (leftSide) {
                            horizontalPosition / (size.width / 2)
                        } else {
                            (size.width - horizontalPosition) / (size.width / 2)
                        }
                        val rotationalOffset = max(1f, (1f - offsetXRatioFromMiddle) * 4f)
                        launch {
                            offsetY.snapTo(verticalDragOffset)
                            rotation.snapTo(if (leftSide) rotationalOffset else -rotationalOffset)
                        }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        if (change.positionChange() != Offset.Zero) change.consume()
                    }
                }
                val velocity = velocityTracker.calculateVelocity().y
                val targetOffsetY = decay.calculateTargetValue(offsetY.value, velocity)
                if (targetOffsetY.absoluteValue <= size.height) {
                    // Not enough velocity; Reset.
                    launch { offsetY.animateTo(targetValue = 0f, initialVelocity = velocity) }
                    launch { rotation.animateTo(targetValue = 0f, initialVelocity = velocity) }
                } else {
                    // Enough velocity to fling the card to the back
                    val boomerangDuration = 600
                    val maxDistanceToFling = (size.height * 4).toFloat()
                    val maxRotations = 3
                    val easeInOutEasing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)
                    val distanceToFling = min(
                        targetOffsetY.absoluteValue + size.height, maxDistanceToFling
                    )
                    val rotationToFling = min(
                        360f * (targetOffsetY.absoluteValue / size.height).roundToInt(),
                        360f * maxRotations
                    )
                    val rotationOvershoot = rotationToFling + 12f
                    val animationJobs = listOf(
                        launch {
                            rotation.animateTo(targetValue = if (leftSide) rotationToFling else -rotationToFling,
                                initialVelocity = velocity,
                                animationSpec = keyframes {
                                    durationMillis = boomerangDuration
                                    0f at 0 with easeInOutEasing
                                    (if (leftSide) rotationOvershoot else -rotationOvershoot) at boomerangDuration - 50 with LinearOutSlowInEasing
                                    (if (leftSide) rotationToFling else -rotationToFling) at boomerangDuration
                                })
                            rotation.snapTo(0f)
                        },
                        launch {
                            offsetY.animateTo(targetValue = 0f,
                                initialVelocity = velocity,
                                animationSpec = keyframes {
                                    durationMillis = boomerangDuration
                                    -distanceToFling at (boomerangDuration / 2) with easeInOutEasing
                                    40f at boomerangDuration - 70
                                }
                            ) {
                                if (value <= -size.height * 2 && !clearedHurdle) {
                                    onMoveToBack()
                                    clearedHurdle = true
                                }
                            }
                        }
                    )
                    animationJobs.joinAll()
                    clearedHurdle = false
                }
            }
        }
    }
        .offset { IntOffset(0, offsetY.value.roundToInt()) }
        .graphicsLayer {
            transformOrigin = TransformOrigin.Center
            rotationZ = rotation.value
        }
}

val cardsColors = listOf(
    Color(0xff90caf9),
    Color(0xfffafafa),
    Color(0xffef9a9a),
    Color(0xfffff59d),
).reversed()