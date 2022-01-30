package org.desperu.nativandroidearth.bridge

import android.util.Log
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayer.UnitySendMessage
import org.desperu.nativandroidearth.utils.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

/**
 * EarthBridge used to communicate with the Unity Engine.
 */
interface EarthBridge {

    /**
     * Update the rotation speed of the Earth Game Object.
     *
     * @param value the rotation value to apply.
     */
    fun updateRotation(value: Int)

    /**
     * Reset the rotation speed of the Earth Game Object.
     */
    fun resetRotation()

    /**
     * Update the zoom of the Earth Game Object.
     *
     * @param value the scale values to apply.
     */
    fun updateZoom(value: Int)

    /**
     * Reset the zoom of the Earth Game Object.
     */
    fun resetZoom()
}

/**
 * EarthBridge implementation, used to communicate with the Unity Engine.
 * We can send message, call function and pass param, to Unity C# script,
 * or received call from Unity, with methods names.
 * With support of unity libs [UnityPlayer.UnitySendMessage].
 *
 * @constructor Instantiate a new CommunicationBridge.
 *
 * @property earthBridge    the interface of this EarthBridge instance.
 */
class EarthBridgeImpl : EarthBridge, KoinComponent {

    // FOR COMMUNICATION
    private val earthBridge: EarthBridge by inject { parametersOf(this) }

    // -----------------
    // METHODS OVERRIDES
    // -----------------

    /**
     * Update the rotation speed of the Earth Game Object.
     *
     * @param value the scale values to apply.
     */
    override fun updateRotation(value: Int) {
        toEarth(UPDATE_ROTATION, value.toString())
    }

    /**
     * Reset the rotation speed of the Earth Game Object.
     *
     */
    override fun resetRotation() {
        toEarth(RESET_ROTATION, "")
    }

    /**
     * Update the zoom of the Earth Game Object.
     *
     * @param value the scale values to apply.
     */
    override fun updateZoom(value: Int) {
        toEarth(UPDATE_SCALE, value.toString())
    }

    /**
     * Reset the zoom of the Earth Game Object.
     */
    override fun resetZoom() {
        toEarth(RESET_SCALE, "")
    }

    // -----------------
    // UTILS
    // -----------------

    /**
     * Send message to Earth, Unity game object.
     *
     * @param functionName  the name of C# function into unity script.
     * @param params        the params to send to the function.
     */
    private fun toEarth(functionName: String, params: String) {
        UnitySendMessage(EARTH, functionName, params)
        Log.d("Send Message to Earth", "$EARTH.$functionName, $params")
    }
}