package com.matheus.mota.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.matheus.mota.quizapp.databinding.ActivitySplashScreenBinding



@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        startUsernameActivity()
    }

    private fun startUsernameActivity(){
        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, UsernameActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}