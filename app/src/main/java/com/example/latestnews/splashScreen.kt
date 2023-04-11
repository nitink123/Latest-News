package com.example.latestnews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.latestnews.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: SplashScreenBinding
    private val SPLASH_DELAY_MS = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.animationView.setAnimation("news.json")
        binding.animationView.playAnimation()

     Log.e("splash","splash screen mai enter kr gaya")
        // Code for splash screen
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY_MS.toLong())
    }
}
