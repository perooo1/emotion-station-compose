package com.plenart.emotionstationcompose.ui.info

import androidx.lifecycle.ViewModel
import com.plenart.emotionstationcompose.data.authentication.AuthenticationRepository
import com.plenart.emotionstationcompose.model.User
import com.plenart.emotionstationcompose.ui.info.mapper.InfoScreenMapper

class InfoScreenViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val infoScreenMapper: InfoScreenMapper,
) : ViewModel() {
    private val currentUser = authenticationRepository.currentUser.value

}
