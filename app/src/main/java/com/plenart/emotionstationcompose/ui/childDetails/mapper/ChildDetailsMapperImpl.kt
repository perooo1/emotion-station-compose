package com.plenart.emotionstationcompose.ui.childDetails.mapper

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsUiState

class ChildDetailsMapperImpl : ChildDetailsMapper {
    override fun toChildDetailsUiState(child: Child): ChildDetailsUiState {
        return ChildDetailsUiState(child = child)
    }
}
