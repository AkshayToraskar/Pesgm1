@file:Suppress("unused")

package com.ak.pesgm.app

import android.app.Application
import android.preference.PreferenceManager
import com.ak.pesgm.utils.setupTheme


/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * akshay2211.github.io
 **/
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceManager.getDefaultSharedPreferences(this).setupTheme("list_theme", resources)
    }
}