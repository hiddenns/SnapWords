package com.khalore.features.screens.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import com.khalore.core.base.State
import com.khalore.features.components.Error
import com.khalore.snapwords.R


@Preview
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state = viewModel.viewState.value.state

    when (state) {
        is State.Data -> SettingsContent(state.asData())
        is State.Error -> Error()
        is State.Loading -> Error()
        is State.None -> Error()
    }

}

@Composable
fun SettingsContent(state: SettingsViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        Card(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(.9f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = "About SnapWords",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Image(
                        painterResource(id = R.drawable.ic_snap_words),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }

                Text(
                    text = "SnapWords is an app that helps you learn terms, foreign words, historical dates, and other useful information. All of this is presented in the form of cards that you can swipe to study their content. If you double-tap on a card, it flips to show another word. \nConvenient, isn't it?",
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Stay connected! Now, every swipe in SnapWords brings you closer to your goal!",
                    modifier = Modifier
                        .padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 4.dp),
            onClick = {
                try {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, state.googlePlayLink)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(context, shareIntent, bundleOf())
                } catch (e: ActivityNotFoundException) {
                    Log.e("SettingsScreen", "SettingsContent:", e)
                }

            }) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Share App ")
                Icon(
                    Icons.Rounded.Share,
                    contentDescription = null
                )
            }

        }

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            onClick = {
                val tgIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("tg://openmessage?user_id=${state.telegramContactUserId}")
                )
                tgIntent.setPackage("org.telegram.messenger")

                println("some ${state.telegramContactUserId}, ${state.googlePlayLink}")

                kotlin.runCatching {
                    startActivity(context, tgIntent, bundleOf())
                }.getOrElse {

                }
            }) {
            Text(text = "Contact in Telegram ")
            Icon(
                Icons.AutoMirrored.Rounded.Send,
                contentDescription = null
            )
        }
    }
}