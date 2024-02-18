package com.khalore.features.components.cards

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.khalore.core.model.card.Card

enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

data class AnimatedCard(
    val card: Card,
    var cardFace: CardFace
) {
    fun next() = this.copy(cardFace = cardFace.next)

}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@Composable
fun FlipCard(
    animatedCard: AnimatedCard,
    onClick: (AnimatedCard) -> Unit,
    modifier: Modifier = Modifier,
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
    onMoveToBack: (animatedCard: AnimatedCard) -> Unit,
    isSwappable: Boolean = false,
    onChangeCardOffsetY: (Pair<Boolean, Animatable<Float, AnimationVector1D>>) -> Unit = {}
) {
    val rotation = animateFloatAsState(
        targetValue = animatedCard.cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ), label = ""
    )
    Card(
        modifier = modifier
            .graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation.value
                } else {
                    rotationY = rotation.value
                }
                cameraDistance = 12f * density
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onClick(animatedCard)
                    }
                )
            }.let { mdf ->
                if (isSwappable)
                    mdf.swipeToBack(onChangeCardOffsetY) {
                        onMoveToBack(animatedCard)
                        if (rotation.value > 90f) onClick(animatedCard)
                    }
                else {
                    mdf
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
    ) {
        if (rotation.value <= 90f) {
            front()
        } else {
            Box(
                Modifier
                    .graphicsLayer {
                        if (axis == RotationAxis.AxisX) {
                            rotationX = 180f
                        } else {
                            rotationY = 180f
                        }
                    },
            ) {
                back()
            }
        }
    }
}