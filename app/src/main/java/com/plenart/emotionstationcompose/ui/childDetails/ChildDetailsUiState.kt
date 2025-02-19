package com.plenart.emotionstationcompose.ui.childDetails

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.ui.graphics.vector.ImageVector
import com.plenart.emotionstationcompose.R
import com.plenart.emotionstationcompose.model.ActivityRecord
import com.plenart.emotionstationcompose.model.Child


data class ChildDetailsUiState(
    val child: Child = Child(),
    val recordedActivities: List<ActivityRecord> = emptyList(),
    val tabState: ChildDetailsTabState = ChildDetailsTabState(),
)

data class ChildDetailsTabState(
    val primaryTabs: List<ESTab> = listOf(
        ESTab(R.string.child_info_tab_label, Icons.Outlined.Face),
        ESTab(R.string.child_activities_tab_label, Icons.Outlined.DateRange),
    ),
    val secondaryTabs: List<ESTab> = listOf(
        ESTab(R.string.child_overview_tab_label),
        ESTab(R.string.child_completed_activities_tab_label),

        ),
    val selectedPrimaryTabIndex: Int = 0,
    val selectedSecondaryTabIndex: Int = 0,
)

data class ESTab(
    @StringRes val labelRes: Int,
    val icon: ImageVector? = null,
)
