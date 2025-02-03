package com.plenart.emotionstationcompose.ui.childDetails.mapper

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsUiState

interface ChildDetailsMapper {
    fun toChildDetailsUiState(child: Child): ChildDetailsUiState
}
