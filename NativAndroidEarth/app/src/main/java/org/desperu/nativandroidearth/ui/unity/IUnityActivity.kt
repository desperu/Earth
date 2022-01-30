package org.desperu.nativandroidearth.ui.unity

/**
 * Unity Activity interface that is used for communication.
 */
interface IUnityActivity {

    /**
     * Call from Unity to display the current rotation speed value of the Earth.
     *
     * @param rotation  the current rotation value.
     */
    fun displayRotation(rotation: String)
}