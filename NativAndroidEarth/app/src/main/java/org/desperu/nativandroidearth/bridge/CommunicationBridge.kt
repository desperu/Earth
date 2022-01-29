package org.desperu.nativandroidearth.bridge

import com.unity3d.player.UnityPlayer

/**
 * TODO
 */
class CommunicationBridge(
    private val communicationCallback: CommunicationCallback
) {
    fun callFromUnityWithNoParameters() {
        communicationCallback.onNoParamCall()
    }

    fun callFromUnityWithOneParameter(param: String) {
        communicationCallback.onOneParamCall(param)
    }

    fun callFromUnityWithTwoParameters(param1: String, param2: Int) {
        communicationCallback.onTwoParamCall(param1, param2)
    }

    fun callToUnityWithNoMessage() {
        UnityPlayer.UnitySendMessage("Cube",
            "CallFromAndroidWithNoMessage", "")
    }

    fun callToUnityWithMessage(param: String) {
        UnityPlayer.UnitySendMessage("Cube",
            "CallFromAndroidWithMessage", param)
    }

    interface CommunicationCallback {
        fun onNoParamCall()
        fun onOneParamCall(param: String)
        fun onTwoParamCall(param1: String, param2: Int)
    }
}