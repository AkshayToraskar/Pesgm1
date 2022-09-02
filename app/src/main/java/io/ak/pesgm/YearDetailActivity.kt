package io.ak.pesgm

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.ak.pesgm.adapter.FeaturesImagesAdapter
import io.ak.pesgm.app.SessionManager
import io.ak.pesgm.databinding.ActivityYearDetailBinding
import io.ak.pesgm.interfaces.RecyclerviewOnClickListener
import io.ak.pesgm.utils.isDarkThemeOn
import io.ak.pesgm.utils.setUpStatusNavigationBarColors


class YearDetailActivity : AppCompatActivity(), RecyclerviewOnClickListener {

    val TAG = "Year Detail Activity"
    private lateinit var binding: ActivityYearDetailBinding
    lateinit var listener: RecyclerviewOnClickListener

    var features_images = ArrayList<String>()

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
        listener = this

        val sessionManager = SessionManager(this)

        var year: String? = null
        var info: String? = null

        var doc_id = intent.getStringExtra("doc_id").toString()
        var en_year = intent.getStringExtra("en_year").toString()
        var mr_year = intent.getStringExtra("mr_year").toString()
        var en_info = intent.getStringExtra("en_info").toString()
        var mr_info = intent.getStringExtra("mr_info").toString()
        var img_path = intent.getStringExtra("img_path").toString()
        if(intent.getStringArrayListExtra("features_images")!=null) {
            features_images = intent.getStringArrayListExtra("features_images")!!
        }


        Glide.with(this)
            .load(img_path)
            .placeholder(R.drawable.progress_animation)
            .into(object : CustomTarget<Drawable?>() {
                @SuppressLint("SetTextI18n")
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    binding.ivDetailPic.setImageDrawable(resource)
                }
                override fun onLoadCleared(@Nullable placeholder: Drawable?) = Unit
            })


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

        if(features_images==null){
//            binding.cvFeaturesImages.visibility = View.GONE
        }else{
//            binding.cvFeaturesImages.visibility = View.VISIBLE
            val gridLayoutManager = GridLayoutManager(this,3)
            binding.rvFeaturesImages.layoutManager = gridLayoutManager
            var featuresImagesAdapter = FeaturesImagesAdapter(listener, features_images as ArrayList<String>, view.context)
            binding.rvFeaturesImages.adapter = featuresImagesAdapter
            binding.rvFeaturesImages.isNestedScrollingEnabled = false

        }

        binding.tvYear.text = year
        binding.tvInfo.text = info

        binding.ivBack.setOnClickListener{
            finish()
        }

        binding.ivDetailPic.setOnTouchListener(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                when (p1?.getAction()) {
                    MotionEvent.ACTION_DOWN -> binding.csNestedView.setScrollingEnabled(false)
                    MotionEvent.ACTION_MOVE -> {
                        if (p1.getPointerCount() == 2) {
                            binding.csNestedView.setScrollingEnabled(false)
                        }else{
                            binding.csNestedView.setScrollingEnabled(true)
                        }
                    }
                    MotionEvent.ACTION_UP -> binding.csNestedView.setScrollingEnabled(true)
                }
                return true
            }
        }
        )
    }

    override fun recyclerviewClick(position: Int) {
        val i = Intent(this, StoriesActivity::class.java)
        i.putStringArrayListExtra("features_images", features_images)
        i.putExtra("counter",position)
        startActivity(i)
    }
}