package com.plenart.emotionstationcompose.ui.authentication.signUp.di

import com.plenart.emotionstationcompose.ui.authentication.signUp.SignUpViewModel
import com.plenart.emotionstationcompose.ui.authentication.signUp.mapper.SignUpMapper
import com.plenart.emotionstationcompose.ui.authentication.signUp.mapper.SignUpMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
    viewModel {
        SignUpViewModel(
            authenticationRepository = get(),
            signUpMapper = get(),
        )
    }

    single<SignUpMapper> { SignUpMapperImpl() }
}

