package com.plenart.emotionstationcompose.ui.activity.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plenart.emotionstationcompose.ui.activity.QuestionUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EsBottomSheet(
    questions: List<QuestionUiState>,
    recordActivity: () -> Unit,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(80.dp)
            .background(Color.Magenta)
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage > 0) {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            },
        ) { Text("back") }
        EsPagerIndicator(questions.size, pagerState.currentPage)
        TextButton(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < questions.size - 1) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }else{
                        recordActivity()
                    }
                }
            },
        ) { Text("forward") }
    }
}

@Preview(showBackground = true)
@Composable
private fun EsBottomSheetPreview() {
    //EsBottomSheet()
}
