package com.plenart.emotionstationcompose.ui.children.di

import com.plenart.emotionstationcompose.ui.children.ChildrenScreenViewModel
import com.plenart.emotionstationcompose.ui.children.mapper.ChildrenScreenMapper
import com.plenart.emotionstationcompose.ui.children.mapper.ChildrenScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val childrenScreenModule = module {
    viewModel {
        ChildrenScreenViewModel(
            authenticationRepository = get(),
            databaseRepository = get(),
            childrenScreenMapper = get(),
        )
    }
    single<ChildrenScreenMapper> { ChildrenScreenMapperImpl() }
}
