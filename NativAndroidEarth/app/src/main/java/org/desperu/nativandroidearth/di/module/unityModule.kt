package org.desperu.nativandroidearth.di.module

import org.desperu.nativandroidearth.ui.unity.IUnityActivity
import org.desperu.nativandroidearth.ui.unity.UnityActivity
import org.koin.dsl.module

/**
 * Koin module which provide dependencies related to unity activity.
 */
val unityModule = module {

    /**
     * Provides an interface of IUnityActivity instance.
     */
    single<IUnityActivity> { (activity: UnityActivity) ->
        activity
    }
}
