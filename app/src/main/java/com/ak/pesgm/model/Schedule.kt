package com.ak.pesgm.model
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.Timestamp

@IgnoreExtraProperties
class Schedule {
    var date: Timestamp? = null
    var day: String? = null
    var mr_info: String? = null
    var en_info: String? = null
}