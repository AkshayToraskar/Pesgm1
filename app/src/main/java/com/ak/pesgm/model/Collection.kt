package com.ak.pesgm.model
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Collection {
    var mr_info: String? = null
    var en_info: String? = null
    var mr_year: String? = null
    var en_year: String? = null
    var img_path: String? = null
    var img_thumb: String? = null
}