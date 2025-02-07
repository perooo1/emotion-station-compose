package com.plenart.emotionstationcompose.ui.childDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsViewModel
import com.plenart.emotionstationcompose.ui.theme.localSpacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChildInfoCard(
    child: Child,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer,),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(localSpacing.current.medium),
            modifier = Modifier.padding(localSpacing.current.medium)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Diagnosis")
                Text(child.diagnosis)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Child age ")
                Text(child.age.toString() ?: "")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start of treatment")
                Text(child.treatmentStartMonth)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Attends kindergarten?")
                Text(child.attendsKindergarten.toString())
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Risky pregnancy?")
                Text(child.riskyPregnancy.toString())
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pregnancy birth week")
                Text(child.pregnancyBirthWeek.toString() ?: "")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ChildInfoCardPreview() {
    val viewModel = koinViewModel<ChildDetailsViewModel>()
    val state = viewModel.uiState.collectAsState().value

    ChildInfoCard(child = state.child)
}
