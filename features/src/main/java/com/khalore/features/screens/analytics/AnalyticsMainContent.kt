package com.khalore.features.screens.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.khalore.features.components.SoonDevelopButton
import com.khalore.features.screens.analytics.charts.SwipesColumnBarChart
import com.khalore.snapwords.R

@Composable
fun AnalyticsScreenMainContent(
    state: AnalyticsViewState,
    onEventSent: (event: AnalyticsScreenContract.Event) -> Unit,
) {

    Column(
        Modifier
            .padding(top = 24.dp, start = 8.dp, end = 8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipesColumnBarChart(state.weekDailyAnalyticsList.reversed())
        TextToNumberAnalyticList(state.textToNumberAnalyticsList, onClickSoonButton = {
            onEventSent(AnalyticsScreenContract.Event.OnClickSoonButton)
        })
    }
}

@Composable
fun TextToNumberAnalyticList(
    analyticsList: List<TextToNumberAnalyticsItemUI>,
    onClickSoonButton: () -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(analyticsList) { item ->
            when (item.viewType) {
                AnalyticsViewType.DATA -> ItemTextToNumber(item)
                AnalyticsViewType.MORE -> MoreAnalyticsButton(onClickSoonButton = onClickSoonButton)
            }
        }
    }
}

@Composable
fun MoreAnalyticsButton(
    onClickSoonButton: () -> Unit
) {
    SoonDevelopButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        text = stringResource(id = R.string.more_analytics),
        description = stringResource(id = R.string.develop_soon_analytics),
        onClickSoonButton = onClickSoonButton
    )
}


@Composable
@Preview(locale = "uk")
fun ItemTextToNumber(
    @PreviewParameter(TextToNumberAnalyticsItemParameter::class) item: TextToNumberAnalyticsItemUI
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            hoveredElevation = 20.dp
        ),

        ) {
        Row(
            Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(
                    id = item.icon
                ),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp),
            )

            Text(
                text = stringResource(id = item.message),
                modifier = Modifier.fillMaxWidth(.7f),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier)
            Text(
                text = item.count.toString(), modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}

class TextToNumberAnalyticsItemParameter : PreviewParameterProvider<TextToNumberAnalyticsItemUI> {
    override val values = sequenceOf(
        TextToNumberAnalyticsItemUI(
            count = 11043521,
            message = R.string.total_cards,
            icon = androidx.core.R.drawable.ic_call_decline
        )
    )
}
