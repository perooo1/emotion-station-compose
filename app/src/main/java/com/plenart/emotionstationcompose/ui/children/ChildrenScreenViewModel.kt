package com.plenart.emotionstationcompose.ui.children

import androidx.lifecycle.ViewModel
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.ui.children.mapper.ChildrenScreenMapper

class ChildrenScreenViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val childrenScreenMapper: ChildrenScreenMapper,
    private val databaseRepository: RemoteDatabaseRepository,
): ViewModel() {

}
