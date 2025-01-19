package com.plenart.emotionstationcompose.ui.children.mapper

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.children.ChildrenScreenUiState

interface ChildrenScreenMapper {
    fun toChildrenScreenUiState(children: List<Child>) : ChildrenScreenUiState
}
