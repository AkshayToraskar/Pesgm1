@file:Suppress("DEPRECATION")

package com.ak.pesgm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ak.pesgm.fragment.HomeFragment
import com.ak.pesgm.fragment.ScheduleFragment
import com.ak.pesgm.fragment.SettingFragment


@Suppress("DEPRECATION")
class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var list = arrayListOf("Home", "Logger", "Settings")


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ScheduleFragment()
            2 -> SettingFragment()
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return list.size
    }
}
