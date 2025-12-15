package com.bounteous.analytics

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics


class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private var redCount = 0
    private var greenCount = 0
    private var blueCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAnalytics = Firebase.analytics
        val btn1 = findViewById<AppCompatButton>(R.id.btnOne)
        val btn2 = findViewById<AppCompatButton>(R.id.btnTwo)
        val btn3 = findViewById<AppCompatButton>(R.id.btnThree)
        btn1.setOnClickListener {
            redCount++
            logEvent("Red", redCount)
        }
        btn2.setOnClickListener {
            greenCount++
            logEvent("Green", greenCount)
        }
        btn3.setOnClickListener {
            blueCount++
            logEvent("Blue", blueCount)
        }
    }

    private fun logEvent(buttonName: String, count: Int){
        val params = Bundle().apply {
            putString("button_name", buttonName)
            putInt("click_count", count)
        }

        firebaseAnalytics.logEvent("button_clicked", params)
        //CI/CD trigger test
    }
}