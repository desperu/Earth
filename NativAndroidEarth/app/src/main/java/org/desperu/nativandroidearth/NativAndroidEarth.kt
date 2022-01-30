package org.desperu.nativandroidearth

import android.app.Application
import org.desperu.nativandroidearth.di.module.bridgeModule
import org.desperu.nativandroidearth.di.module.ui.unityModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NativAndroidEarth : Application() {

    /**
     * Initializes the application, by adding strict mode and starting koin.
     */
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NativAndroidEarth)
            modules(
                listOf(
                    bridgeModule,
                    unityModule
                )
            )
        }
    }
}