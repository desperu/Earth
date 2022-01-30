package org.desperu.nativandroidearth.di.module

import org.desperu.nativandroidearth.bridge.EarthBridge
import org.desperu.nativandroidearth.bridge.EarthBridgeImpl
import org.koin.dsl.module

/**
 * Koin module which provide dependencies related to bridge.
 */
val bridgeModule = module {

    /**
     * Provides an interface of EarthBridge instance.
     */
    single<EarthBridge> {
        EarthBridgeImpl()
    }
}
