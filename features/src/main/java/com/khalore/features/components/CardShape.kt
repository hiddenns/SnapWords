package com.khalore.features.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp


@Composable
fun CardShapeCanvas(
    color: Brush
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .width(40.dp)
                .height(20.dp),
        ) {

//            rotate(degrees = 14f) {
//                drawRoundRect(
//                    color = Color.Blue,
//                    cornerRadius = CornerRadius(
//                        12f, 12f
//                    ),
//                    topLeft = Offset(0f, -8.dp.toPx())
//                )
//            }
//
//            rotate(degrees = 8f) {
//                drawRoundRect(
//                    color = Color.Green,
//                    cornerRadius = CornerRadius(
//                        12f, 12f
//                    ),
//                    topLeft = Offset(0f, -4.dp.toPx())
//                )
//            }

            drawRoundRect(
                brush = color,
                cornerRadius = CornerRadius(
                    12f, 12f
                )
            )
        }
    }
}