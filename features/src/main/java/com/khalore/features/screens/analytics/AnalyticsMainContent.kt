package com.khalore.features.screens.analytics

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.khalore.core.model.analytics.DailyAnalytic
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.VerticalPosition
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun AnalyticsScreenMainContent(
    state: AnalyticsViewState,
) {

    Log.d("anal", "AnalyticsScreenMainContent: ${state}")
    Column(
        Modifier
            .padding(vertical = 32.dp)
            .fillMaxSize()
    ) {

        MyBarChartParent(state.weekDailyAnalyticsList.reversed())

        Column {
            state.textToNumberAnalyticsList.forEach {
                key(it.message) {
                    Row {
                        Text(text = it.message)
                        Text(text = " --- ")
                        Text(text = it.count.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun MyBarChartParent(reversed: List<DailyAnalytic>) {

    val data = reversed.associate {
        val instant = Instant.ofEpochMilli(it.dayUtc)
        val localDate = instant.atZone(ZoneOffset.UTC).toLocalDate()
        localDate to it.swipesCount
    }

    val xValuesToDates = data.keys.associateBy { it.toEpochDay().toFloat() }
    val chartEntryModel = entryModelOf(xValuesToDates.keys.zip(data.values, ::entryOf))
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM")
    val horizontalAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            (xValuesToDates[value]
                ?: LocalDate.ofEpochDay(value.toLong())).format(dateTimeFormatter)
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "Swipes chart",
                modifier = Modifier.padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Chart(
                chart = columnChart(
                    columns = listOf(
                        LineComponent(
                            color = MaterialTheme.colorScheme.surface.toArgb(),
                            thicknessDp = 8f,
                            shape = Shapes.roundedCornerShape(allPercent = 40),
                        )
                    ),
                    dataLabelVerticalPosition = VerticalPosition.Top
                ),
                model = chartEntryModel,
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = horizontalAxisValueFormatter
                )
            )
        }
    }
}