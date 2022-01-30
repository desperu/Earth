package org.desperu.nativandroidearth.base

import android.os.Bundle
import android.view.View
import com.unity3d.player.UnityPlayerActivity

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

/**
 * Abstract base activity class witch provide standard functions for activities,
 * and extends [UnityPlayerActivity] to implement player and functionalities.
 *
 * @param module the koin module to load for the corresponding activity.
 */
abstract class BaseUnityPlayerActivity(private vararg val module: Module): UnityPlayerActivity() {

    init {
        unloadKoinModules(module.toList())
        loadKoinModules(module.toList())
    }

    // --------------------
    // BASE METHODS
    // --------------------

    protected abstract fun getActivityView(): View
    protected abstract fun configureDesign()

    // --------------------
    // LIFE CYCLE
    // --------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(getActivityView())
        configureDesign()
    }

    override fun onDestroy() {
        unloadKoinModules(module.toList())
        super.onDestroy()
    }
}