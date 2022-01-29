package org.desperu.nativandroidearth.bridge

import com.unity3d.player.UnityPlayer

object CommunicationBridge {

    fun callToUnityWithNoMessage() {
        UnityPlayer.UnitySendMessage("Earth", "CallFromAndroidWithNoMessage", "")
    }

    fun callToUnityWithMessage(param: String) {
        UnityPlayer.UnitySendMessage("Earth", "CallFromAndroidWithMessage", param)
    }
}