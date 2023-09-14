package com.hazel.internshipproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("LifeCycle","OnCreate Act1")
        button=findViewById(R.id.btn)
        button.setOnClickListener{
            startActivity(Intent(this,HomePage::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle","OnStart Act1")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle","OnDestroy Act1")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle","OnPause Act1")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle","OnResume Act1")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle","OnStop Act1")
    }

}