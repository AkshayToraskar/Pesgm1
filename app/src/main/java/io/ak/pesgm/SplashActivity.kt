package io.ak.pesgm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.ak.pesgm.app.SessionManager
import io.ak.pesgm.databinding.ActivitySplashBinding
import io.ak.pesgm.utils.isDarkThemeOn
import io.ak.pesgm.utils.setUpStatusNavigationBarColors
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

        binding.tvAppVersion.text = "V " + BuildConfig.VERSION_NAME

        sessionManager = SessionManager(this)
        if (sessionManager.language == "mr") {
            setLocale("mr")
            binding.tvTitle.setTypeface(resources.getFont(R.font.amsmanthan))
            binding.tvTitle.setTextSize(23f)
            binding.tvTitle.setText(resources.getString(R.string.title))
        } else if (sessionManager.language == "en") {
            setLocale("en")
            binding.tvTitle.setTypeface(resources.getFont(R.font.josefinsansregular))
            binding.tvTitle.setTextSize(23f)
            binding.tvTitle.setText(resources.getString(R.string.title))
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
