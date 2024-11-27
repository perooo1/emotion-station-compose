package com.plenart.emotionstationcompose

import android.app.Application
import android.util.Log
import authenticationModule
import com.plenart.emotionstationcompose.data.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EmotionStationComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@EmotionStationComposeApp)
            modules(
                authenticationModule,
                databaseModule,
            )
        }
        Log.d("EmotionStationComposeApp", "App started")

    }
}
