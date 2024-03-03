package com.khalore.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalore.snapwords.R

@Preview
@Composable
fun EmptyCardsScreen(
    onClickInfo: () -> Unit = {},
    onClickCollection: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeEmptyCard(onClickCollection)
            InfoSection(onClickInfo)
        }
    }
}

@Composable
fun WelcomeEmptyCard(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.dont_have_any_cards),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            FilledTonalButton(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = onClick
            ) {
                Text(
                    text = stringResource(id = R.string.add_cards),
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun InfoSection(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
            .background(color = MaterialTheme.colorScheme.tertiary)
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.learn_more_about_application),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            OutlinedButton(
                modifier = Modifier
                    .padding(top = 12.dp),
                onClick = onClick,
            ) {
                Text(
                    text = stringResource(id = R.string.about_snap_words),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}