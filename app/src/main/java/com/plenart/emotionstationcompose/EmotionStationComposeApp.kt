package com.plenart.emotionstationcompose

import android.app.Application
import android.util.Log
import authenticationModule
import com.plenart.emotionstationcompose.data.di.databaseModule
import com.plenart.emotionstationcompose.ui.authentication.signIn.di.signInModule
import com.plenart.emotionstationcompose.ui.authentication.signUp.di.signUpModule
import com.plenart.emotionstationcompose.ui.children.di.childrenScreenModule
import com.plenart.emotionstationcompose.ui.info.di.infoScreenModule
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
                childrenScreenModule,
                databaseModule,
                infoScreenModule,
                signInModule,
                signUpModule,
            )
        }
        Log.d("EmotionStationComposeApp", "App started")
    }
}
