package org.desperu.nativandroidearth.di.module.ui

import org.desperu.nativandroidearth.ui.unity.IUnityActivity
import org.desperu.nativandroidearth.ui.unity.UnityActivity
import org.koin.dsl.module

/**
 * Unity activity module which provide dependencies related to activity.
 */
val unityModule = module {

    /**
     * Provides an UnityActivity instance.
     */
    single<IUnityActivity> { (activity: UnityActivity) ->
        activity
    }
}