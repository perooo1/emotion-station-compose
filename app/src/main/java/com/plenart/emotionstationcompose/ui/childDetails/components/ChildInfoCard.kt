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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.R
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(localSpacing.current.medium),
            modifier = Modifier.padding(localSpacing.current.medium)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.child_diagnosis))
                Text(child.diagnosis)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.child_age))
                Text(child.age.toString())
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.child_treatment_start))
                Text(child.treatmentStartMonth)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.child_attends_kindergarten))
                Text(child.attendsKindergarten.toString())
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.child_risky_pregnancy))
                Text(child.riskyPregnancy.toString())
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.child_pregnancy_birth_week))
                Text(child.pregnancyBirthWeek.toString())
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
