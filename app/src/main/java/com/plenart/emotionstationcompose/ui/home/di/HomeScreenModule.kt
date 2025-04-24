package com.plenart.emotionstationcompose.ui.home.di

import com.plenart.emotionstationcompose.ui.home.HomeScreenViewModel
import com.plenart.emotionstationcompose.ui.home.mapper.HomeScreenMapper
import com.plenart.emotionstationcompose.ui.home.mapper.HomeScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeScreenModule = module {
    viewModel {
        HomeScreenViewModel(
            homeScreenMapper = get(),
            databaseRepository = get(),
            authenticationRepository = get(),
        )
    }
    single<HomeScreenMapper> { HomeScreenMapperImpl() }
}
