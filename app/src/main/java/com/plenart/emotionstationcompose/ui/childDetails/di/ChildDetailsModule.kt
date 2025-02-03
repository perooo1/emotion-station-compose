package com.plenart.emotionstationcompose.ui.childDetails.di

import com.plenart.emotionstationcompose.ui.childDetails.ChildDetailsViewModel
import com.plenart.emotionstationcompose.ui.childDetails.mapper.ChildDetailsMapper
import com.plenart.emotionstationcompose.ui.childDetails.mapper.ChildDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val childDetailsModule = module {
    viewModel { params ->
        ChildDetailsViewModel(
            childId = params.get(),
            mapper = get(),
            databaseRepository = get(),
        )

    }
    single<ChildDetailsMapper> { ChildDetailsMapperImpl() }
}
