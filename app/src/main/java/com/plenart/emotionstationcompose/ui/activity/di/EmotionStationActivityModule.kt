package com.plenart.emotionstationcompose.ui.activity.di

import com.plenart.emotionstationcompose.ui.activity.EmotionStationActivityViewModel
import com.plenart.emotionstationcompose.ui.activity.mapper.EmotionStationActivityMapper
import com.plenart.emotionstationcompose.ui.activity.mapper.EmotionStationActivityMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val emotionStationActivityModule = module {
    viewModel { params ->
        EmotionStationActivityViewModel(
            selectedChildId = params.get(),
            databaseRepository = get(),
            storageRepository = get(),
            questionRepository = get(),
            mapper = get()
        )
    }
    single<EmotionStationActivityMapper> { EmotionStationActivityMapperImpl() }
}
