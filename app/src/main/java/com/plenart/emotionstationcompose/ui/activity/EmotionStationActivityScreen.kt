package com.plenart.emotionstationcompose.ui.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.ui.activity.components.EsPager


@Composable
fun EmotionStationActivityScreen(
    state: EmotionStationActivityUiState,
    onActivityCompletedDialogDismiss: () -> Unit,
    onOptionSelected: (Int, Int, ComprehensionLevel) -> Unit,
    onQuestionVisible: (Int) -> Unit,
    recordActivity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    EsPager(
        questions = state.questions,
        onQuestionVisible = onQuestionVisible,
        onOptionSelected = onOptionSelected,
        recordActivity = recordActivity,
        onActivityCompletedDialogDismiss = onActivityCompletedDialogDismiss,
        activityCompleted = state.activityCompleted,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun EmotionStationActivityScreenPreview(modifier: Modifier = Modifier) {
    //EmotionStationActivityScreen(selectedChildId = "lalala")
}
