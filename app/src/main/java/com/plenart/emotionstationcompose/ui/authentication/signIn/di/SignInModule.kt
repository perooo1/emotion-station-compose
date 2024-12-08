package com.plenart.emotionstationcompose.ui.authentication.signIn.di

import com.plenart.emotionstationcompose.ui.authentication.signIn.SignInViewModel
import com.plenart.emotionstationcompose.ui.authentication.signIn.mapper.SignInMapper
import com.plenart.emotionstationcompose.ui.authentication.signIn.mapper.SignInMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInModule = module {
    viewModel {
        SignInViewModel(
            authenticationRepository = get(),
            signInMapper = get(),
        )
    }

    single<SignInMapper> { SignInMapperImpl() }

}

