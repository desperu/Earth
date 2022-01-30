package org.desperu.nativandroidearth.ui

import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import org.desperu.nativandroidearth.R
import org.desperu.nativandroidearth.base.BaseUnityPlayerActivity
import org.desperu.nativandroidearth.bridge.EarthBridge
import org.desperu.nativandroidearth.di.module.bridgeModule
import org.desperu.nativandroidearth.extension.design.bindView
import org.desperu.nativandroidearth.utils.INITIAL_ROTATION
import org.desperu.nativandroidearth.utils.INITIAL_ZOOM
import org.koin.android.ext.android.inject
import kotlin.math.min

/**
 * Earth Z values.
 */
private const val MIN_Z = -9
private const val MAX_Z = 91

/**
 * Unity Activity activity, that wrap a Unity engine to display 3D animation.
 *
 * @property bridgeModule the koin of the activity to load at start.
 *
 * @constructor Instantiates a new MainActivity.
 */
class UnityActivity : BaseUnityPlayerActivity(bridgeModule) {

    // COMMUNICATION
    private val earthBridge: EarthBridge by inject()
    // FOR DESIGN
    private lateinit var layoutControls: View
    private val resetButton: Button by bindView(R.id.button_reset)
    private val rotationSeekBar: SeekBar by bindView(R.id.rotation_seek_bar)
    private val zoomSeekBar: SeekBar by bindView(R.id.zoom_seek_bar)

    // -----------------
    // BASE METHODS
    // -----------------

    override fun getActivityView(): View = mUnityPlayer

    override fun configureDesign() {
        addLayoutControls()
        addButtonsControlListener()
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    /**
     * Set buttons control listeners to handle Earth Game Object rotation and zoom.
     */
    private fun addButtonsControlListener() {
        resetButton.setOnClickListener {
            rotationSeekBar.progress = INITIAL_ROTATION
            earthBridge.resetRotation()

            zoomSeekBar.progress = INITIAL_ZOOM
            earthBridge.resetZoom()
        }
        rotationSeekBar.setOnSeekBarChangeListener(rotationSeekBarListener)
        zoomSeekBar.setOnSeekBarChangeListener(zoomSeekBarListener)
    }

    // -----------------
    // UI
    // -----------------

    /**
     * Add layout controls buttons layout over the Unity Play View.
     */
    private fun addLayoutControls() {
        layoutControls = layoutInflater.inflate(R.layout.layout_controls, mUnityPlayer)
        layoutControls.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
    }

    // -----------------
    // LISTENERS
    // -----------------

    /**
     * Rotation Seek Bar change listener, to handle new progress value.
     */
    private val rotationSeekBarListener = object : OnSeekBarChangeListener {

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            earthBridge.updateRotation(p1)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {}

        override fun onStopTrackingTouch(p0: SeekBar?) {}
    }

    /**
     * Zoom Seek Bar change listener, to handle new progress value.
     */
    private val zoomSeekBarListener = object : OnSeekBarChangeListener {

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            // Correct zoom value to be able to zoom in and zoom out
            val zoom = min(p1 + MIN_Z, MAX_Z)
            earthBridge.updateZoom(zoom)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {}

        override fun onStopTrackingTouch(p0: SeekBar?) {}
    }
}