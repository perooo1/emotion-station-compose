package com.plenart.emotionstationcompose.ui.childDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.plenart.emotionstationcompose.model.ActivityRecord
import java.time.format.DateTimeFormatter

@Composable
fun CompletedActivitiesSecondaryTab(
    completedActivities: List<ActivityRecord>,
    onCompletedActivityAction: (ActivityRecord) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is completed activities secondary tab")

        LazyColumn(
            modifier = modifier,
        ) {
            items(
                items = completedActivities,
                key = {
                    it.timeOfActivity
                }
            ) {
                Card(
                    modifier = modifier.clickable {
                        onCompletedActivityAction(it)
                    },
                ) {
                    ListItem(
                        headlineContent = { Text(it.emotionStation?.stationName ?: "no name") },
                        supportingContent = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = it.timeOfActivity.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                            }
                        }
                    )
                }
            }
        }
    }
}
