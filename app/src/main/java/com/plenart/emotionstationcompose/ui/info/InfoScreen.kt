package com.plenart.emotionstationcompose.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    infoScreenUiState: InfoScreenUiState,
    onFABAction: () -> Unit,
    onSignOutAction: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(
                            if (infoScreenUiState.isCurrentUserSpecialist)
                                R.string.info_screen_title_specialist
                            else
                                R.string.info_screen_title_parent
                        )
                    )
                },
                actions = {
                    IconButton(
                        content = {
                            Icon(
                                Icons.AutoMirrored.Filled.ExitToApp,
                                contentDescription = ""
                            )
                        },
                        onClick = onSignOutAction,
                    )
                }
            )
        },
        floatingActionButton = {
            if (infoScreenUiState.isCurrentUserSpecialist) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Default.Edit, contentDescription = "") },
                    onClick = onFABAction,
                    text = { Text(stringResource(R.string.info_screen_fab_text)) },
                )
            }
        },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(R.drawable.info_screen_hero),
                contentDescription = stringResource(R.string.authentication_train_img_content_descr),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(dimensionResource(R.dimen.info_screen_hero_image_size))
            )
            Spacer(modifier = Modifier.padding(vertical = MaterialTheme.spacing.small))
            Text(
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                text = infoScreenUiState.specialistFullName,
            )
            Spacer(modifier = Modifier.padding(vertical = MaterialTheme.spacing.small))
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${stringResource(R.string.info_screen_occupation_label)}:")
                    Text(text = infoScreenUiState.occupation)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${stringResource(R.string.info_screen_workplace_label)}:")
                    Text(text = infoScreenUiState.workplace)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${stringResource(R.string.info_screen_business_hours_label)}:")
                    Text(text = infoScreenUiState.businessHours)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${stringResource(R.string.info_screen_phone_number_label)}:")
                    Text(text = infoScreenUiState.phoneNumber)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "${stringResource(R.string.info_screen_additional_education_label)}:")
                    Text(text = infoScreenUiState.additionalEducation)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(
        infoScreenUiState = InfoScreenUiState(
            additionalEducation = "add edu",
            businessHours = "busi hours",
            isCurrentUserSpecialist = true,
            occupation = "Occupation",
            phoneNumber = "phone num",
            specialistFullName = "Name Lastbane",
            workplace = "roap",
        ),
        onFABAction = {},
        onSignOutAction = {},
    )
}
