package com.plenart.emotionstationcompose.ui.activity.di

import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val emotionStationActivityModule = module {
    viewModel { params ->
        EmotionStationActivityViewModel(
            selectedChildId = params.get(),
            storageRepository = get(),
        )
    }
}
