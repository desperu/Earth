package org.desperu.nativandroidearth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.desperu.nativandroidearth.R
import org.desperu.nativandroidearth.ui.unity.UnityActivity

class MainActivity : AppCompatActivity() {

    // -----------------
    // METHODS OVERRIDES
    // -----------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unityButton = findViewById<Button>(R.id.earth_button)
        unityButton.setOnClickListener {
            startActivity(Intent(this, UnityActivity::class.java))
        }
    }
}