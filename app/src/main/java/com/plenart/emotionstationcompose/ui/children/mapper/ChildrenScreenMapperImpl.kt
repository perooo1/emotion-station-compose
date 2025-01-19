package com.plenart.emotionstationcompose.ui.children.mapper

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.ui.children.ChildrenScreenUiState
import com.plenart.emotionstationcompose.ui.children.components.ChildSimpleCardUiState
import java.time.LocalDate

class ChildrenScreenMapperImpl : ChildrenScreenMapper {
    override fun toChildrenScreenUiState(children: List<Child>): ChildrenScreenUiState {
        return ChildrenScreenUiState(
            children = children.map { child ->
                ChildSimpleCardUiState(
                    age = child.age,
                    diagnosis = child.diagnosis,
                    fullName = child.fullName,
                    itemId = child.id,
                    treatmentStart = child.treatmentStartMonth ?: LocalDate.now()
                )

            }
        )
    }
}
