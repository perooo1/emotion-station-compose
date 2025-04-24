package com.plenart.emotionstationcompose.ui.activity.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.ui.activity.QuestionUiState
import com.plenart.emotionstationcompose.ui.theme.localSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsPager(
    questions: List<QuestionUiState>,
    activityCompleted: Boolean,
    onQuestionVisible: (Int) -> Unit,
    onOptionSelected: (Int, Int, ComprehensionLevel) -> Unit,
    onActivityCompletedDialogDismiss: () -> Unit,
    recordActivity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { questions.size })
    val coroutineScope = rememberCoroutineScope()

    val questionsAvailabe = questions.isNotEmpty()

    LaunchedEffect(pagerState.currentPage, questionsAvailabe) {
        if (questionsAvailabe) {
            onQuestionVisible(pagerState.currentPage)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false,
            ) { page ->
                QuestionCard(
                    questions = questions, page = page,
                    onOptionSelectedAction = { selectedOptionIndex, comprehensionLevel ->
                        onOptionSelected(page, selectedOptionIndex, comprehensionLevel)
                    },
                )

                if (activityCompleted) {
                    BasicAlertDialog(
                        onDismissRequest = onActivityCompletedDialogDismiss
                    ) {
                        Column {
                            Text(stringResource(R.string.activity_completed_dialog_title))
                            Spacer(modifier = Modifier.height(localSpacing.current.medium))
                            Text(stringResource(R.string.activity_completed_dialog_message))
                        }
                    }
                }


            }
        }
        EsBottomSheet(
            questions = questions,
            recordActivity = recordActivity,
            coroutineScope = coroutineScope,
            pagerState = pagerState,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EsPagerPreview() {

}
