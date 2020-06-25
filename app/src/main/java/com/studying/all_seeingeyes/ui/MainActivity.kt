package com.studying.all_seeingeyes.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.studying.all_seeingeyes.MyService
import com.studying.all_seeingeyes.R
import kotlinx.android.synthetic.main.main_layout.*

const val EXTRA_STATUS = "com.studying.all_seeingeyes.ui.EXTRA_STATUS"
const val NAME_STATUS = "com.studying.all_seeingeyes.ui.NAME_STATUS"

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    private val stringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        preferences = getSharedPreferences(NAME_STATUS, Context.MODE_PRIVATE)
        val str = if (preferences.getBoolean(EXTRA_STATUS, false))
            "Stop watching" else "Start watching"

        stringBuilder.apply {
            clear()
            append(str)
        }
    }

    override fun onResume() {
        super.onResume()
        my_button.text = stringBuilder
    }

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    fun changeStatus(view: View) {
        val oldState = preferences.getBoolean(EXTRA_STATUS, false)
        val editor = preferences.edit()
        editor.putBoolean(EXTRA_STATUS, !oldState)
        editor.apply()

        if (oldState) {
            stopService(Intent(this, MyService::class.java))
            my_button.text = "Start watching"
        } else {
            startService(Intent(this, MyService::class.java))
            my_button.text = "Stop watching"
        }
    }
}
