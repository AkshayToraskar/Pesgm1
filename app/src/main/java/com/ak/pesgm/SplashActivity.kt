package com.ak.pesgm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ak.pesgm.app.SessionManager
import com.ak.pesgm.databinding.ActivityMainBinding
import com.ak.pesgm.databinding.ActivitySplashBinding
import com.ak.pesgm.utils.isDarkThemeOn
import com.ak.pesgm.utils.setUpStatusNavigationBarColors

import java.util.*


class SplashActivity : AppCompatActivity() {
    var myLocale: Locale? = null
    var SPLASH_TIME_OUT = 4000
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setUpStatusNavigationBarColors(
            isDarkThemeOn(),
            ContextCompat.getColor(this, R.color.background)
        )
        supportActionBar?.hide()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sessionManager = SessionManager(this)
        if (sessionManager.language == "mr") {
            setLocale("mr")
        } else if (sessionManager.language == "en") {
            setLocale("en")
        }else{
            sessionManager.language = "mr"
            setLocale("mr")
        }

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }, SPLASH_TIME_OUT.toLong())
    }

    fun setLocale(lang: String?) {
        myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
    }

}
