package com.plenart.emotionstationcompose.ui.activity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.ui.activity.QuestionUiState
import com.plenart.emotionstationcompose.ui.theme.localSpacing

@Composable
fun QuestionCard(
    questions: List<QuestionUiState>,
    page: Int,
    onOptionSelectedAction: (Int, ComprehensionLevel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.weight(2f))

        Text(questions[page].text)
        Spacer(modifier = Modifier.weight(1f))
        //if (questions[page].imageUrl != null) {
        if (page != 2 || page != 3) {
            AsyncImage(
                model = questions[page].imageUrl,
                contentDescription = "Image",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(questions[page].storyText)
        Spacer(modifier = Modifier.weight(1f))
        questions[page].options.forEachIndexed { index, option ->
            Column {
                AnswerOption(
                    option = option,
                    selected = index == questions[page].selectedOptionIndex,
                    onSelectAction = { onOptionSelectedAction(index, option.comprehensionLevel) },
                    modifier = Modifier.height(50.dp)
                )
                Spacer(modifier = Modifier.height(localSpacing.current.small))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionCardPreview() {

}
