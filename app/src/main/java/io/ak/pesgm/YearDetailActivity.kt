package io.ak.pesgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.ak.pesgm.app.SessionManager
import io.ak.pesgm.databinding.ActivityMainBinding
import io.ak.pesgm.databinding.ActivityYearDetailBinding
import io.ak.pesgm.model.Collection
import io.ak.pesgm.utils.isDarkThemeOn
import io.ak.pesgm.utils.setUpStatusNavigationBarColors

class YearDetailActivity : AppCompatActivity() {

    val TAG = "Year Detail Activity"
    private lateinit var binding: ActivityYearDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setUpStatusNavigationBarColors(
            isDarkThemeOn(),
            ContextCompat.getColor(this, R.color.background)
        )
        supportActionBar?.hide()
        binding = ActivityYearDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sessionManager = SessionManager(this)

        var year: String? = null
        var info: String? = null

        var doc_id = intent.getStringExtra("doc_id").toString()
        var en_year = intent.getStringExtra("en_year").toString()
        var mr_year = intent.getStringExtra("mr_year").toString()
        var en_info = intent.getStringExtra("en_info").toString()
        var mr_info = intent.getStringExtra("mr_info").toString()
        var img_path = intent.getStringExtra("img_path").toString()

        getFeatureImages(doc_id)

        Glide.with(applicationContext)
            .load(img_path)
            .into(binding.ivDetailPic)

        if (sessionManager.language == "mr") {
            year = mr_year
            info = mr_info
        } else if (sessionManager.language == "en") {
            year = en_year
            info = en_info
        }

        if(info==""){
            binding.tvInfo.visibility = View.GONE
        }else{
            binding.tvInfo.visibility = View.VISIBLE
        }

        binding.tvYear.text = year
        binding.tvInfo.text = info

        binding.ivBack.setOnClickListener{
            finish()
        }
    }

    private fun getFeatureImages(doc_id:String){
        val db = Firebase.firestore
        db.document("pesgm_collection/$doc_id")
            .get()
            .addOnSuccessListener { result ->
                var featuresImages = result.get("features_images")
                Log.d(TAG, "$featuresImages")
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}