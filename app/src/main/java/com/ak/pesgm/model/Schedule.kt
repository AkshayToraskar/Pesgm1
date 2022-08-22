package com.ak.pesgm.model
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Schedule {
    var date: String? = null
    var day: String? = null
    var mr_info: String? = null
    var en_info: String? = null
}