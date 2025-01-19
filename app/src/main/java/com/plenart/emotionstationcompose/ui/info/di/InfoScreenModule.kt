package com.plenart.emotionstationcompose.ui.info.di

import com.plenart.emotionstationcompose.ui.info.InfoScreenViewModel
import com.plenart.emotionstationcompose.ui.info.mapper.InfoScreenMapper
import com.plenart.emotionstationcompose.ui.info.mapper.InfoScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val infoScreenModule = module {
    viewModel {
        InfoScreenViewModel(
            authenticationRepository = get(),
            databaseRepository = get(),
            infoScreenMapper = get(),
        )
    }
    single<InfoScreenMapper> { InfoScreenMapperImpl() }
}
