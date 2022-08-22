package com.ak.pesgm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.ak.pesgm.adapter.ViewPagerAdapter
import com.ak.pesgm.app.SessionManager
import com.ak.pesgm.databinding.ActivityMainBinding
import com.ak.pesgm.utils.isDarkThemeOn
import com.ak.pesgm.utils.setUpStatusNavigationBarColors


class MainActivity : AppCompatActivity() {

    val TAG = "Main Activity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setUpStatusNavigationBarColors(
            isDarkThemeOn(),
            ContextCompat.getColor(this, R.color.background)
        )
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sessionManager = SessionManager(this)
        if (sessionManager.language == "mr") {
            binding.tvHeading.setTypeface(resources.getFont(R.font.amsmanthan))
            binding.tvHeading.setTextSize(22f)
        } else if (sessionManager.language == "en") {
            binding.tvHeading.setTypeface(resources.getFont(R.font.josefinsansregular))
            binding.tvHeading.setTextSize(17f)
        }


        val viewPager = binding.viewpager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                binding.bubbleTabBar.setSelected(position)
                when(position){
                    0 -> binding.tvHeading.setTextColor(resources.getColor(R.color.home))
                    1 -> binding.tvHeading.setTextColor(resources.getColor(R.color.schedule))
                    2 -> binding.tvHeading.setTextColor(resources.getColor(R.color.settings))
                }
            }
        })


        binding.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.home -> binding.viewpager.currentItem = 0
                R.id.log -> binding.viewpager.currentItem = 1
                R.id.setting -> binding.viewpager.currentItem = 2
            }
        }
    }


}