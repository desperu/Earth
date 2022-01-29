package org.desperu.nativandroidearth.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.unity3d.player.UnityPlayerActivity
import org.desperu.nativandroidearth.R
import org.desperu.nativandroidearth.bridge.CommunicationBridge

/**
 * TODO
 */
class UnityActivity : UnityPlayerActivity() {

    // FOR DATA
    private var toggle = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.actcivity_unity)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        addButton()

        return super.onCreateView(name, context, attrs)
    }

    // TODO remove use layout...
    /**
     * Add button to over the Unity Play View.
     */
    private fun addButton() {
        val button = Button(this)

        button.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        button.text = "Call with no message"
        button.setOnClickListener {
            if (toggle) {
                button.text = "Call with Message"
                communicationBridge.callToUnityWithNoMessage()
            } else {
                button.text = "Call with no message"
                communicationBridge.callToUnityWithMessage("Hello Unity!")
            }
            toggle = !toggle
        }

        button.setBackgroundColor(Color.GREEN)
        button.setTextColor(Color.RED)
        mUnityPlayer.addView(button)
    }

    // TODO ??
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private val communicationBridge = CommunicationBridge(
        object : CommunicationBridge.CommunicationCallback {
            override fun onNoParamCall() {
                Log.d("UnityCalled", "Callback with no parameter")
            }

            override fun onOneParamCall(param: String) {
                Log.d(
                    "UnityCalled",
                    "Callback with one parameter: $param"
                )
            }

            override fun onTwoParamCall(param1: String, param2: Int) {
                Log.d(
                    "UnityCalled",
                    "Callback with two parameters: $param1, $param2"
                )
            }
        }
    )
}