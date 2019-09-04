package com.alekskuzmin.flyhi.core

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.alekskuzmin.flyhi.core.injection.presentationModule
import com.alekskuzmin.flyhi.core.injection.remoteModule
import com.alekskuzmin.flyhi.core.injection.stateModule
import com.alekskuzmin.flyhi.core.injection.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class FlyHiApp : MultiDexApplication() {

    companion object {
        lateinit var instance: FlyHiApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@FlyHiApp)
            modules(listOf(remoteModule, stateModule, presentationModule, uiModule))
            logger(if (!BuildConfig.DEBUG) EmptyLogger() else AndroidLogger())
        }
    }
}