package io.ak.pesgm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.ak.pesgm.utils.BottomSheetDialog
import jp.shts.android.storiesprogressview.StoriesProgressView
import jp.shts.android.storiesprogressview.StoriesProgressView.StoriesListener


class StoriesActivity : AppCompatActivity(), StoriesListener {

    var pressTime = 0L
    var limit = 500L

    private var storiesProgressView: StoriesProgressView? = null
    private var infoTextView : TextView ?= null
    private var image: ImageView? = null
    private var ivClose: ImageView? = null
    private var ivPlayPause: ImageView? = null


    private val onTouchListener = OnTouchListener { v, event ->
        // getting action on below line.
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressTime = System.currentTimeMillis()
                storiesProgressView!!.pause()
                return@OnTouchListener false
            }
            MotionEvent.ACTION_UP -> {
                val now = System.currentTimeMillis()
                storiesProgressView!!.resume()
                return@OnTouchListener limit < now - pressTime
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)
        storiesProgressView = findViewById<View>(R.id.stories) as StoriesProgressView?
        infoTextView = findViewById<View>(R.id.tv_info) as TextView?
        ivClose = findViewById<View>(R.id.iv_close) as ImageView?
        ivPlayPause = findViewById<View>(R.id.iv_play_pause) as ImageView?
        image = findViewById<View>(R.id.image) as ImageView?;


        features_images = intent.getStringArrayListExtra("features_images") !!
        counter = intent.getIntExtra("counter",0)

        storiesProgressView?.setStoriesCount(features_images.size);
        storiesProgressView?.setStoryDuration(3000L);
        storiesProgressView?.setStoriesListener(this);
        storiesProgressView?.startStories(counter);

        Glide.with(this)
            .load(features_images[counter])
            .into(image!!)
//        image?.setImageResource();

        val reverse = findViewById<View>(R.id.reverse)
        reverse.setOnClickListener {
            storiesProgressView!!.reverse()
        }
        reverse.setOnTouchListener(onTouchListener)

        val skip = findViewById<View>(R.id.skip)
        skip.setOnClickListener {
            storiesProgressView!!.skip()
        }
        skip.setOnTouchListener(onTouchListener)

        var playPause: Boolean = true
        ivPlayPause?.setOnClickListener {
            if(playPause){
//                ivPlayPause?.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
                storiesProgressView?.pause()
                playPause = false
            }else{
//                ivPlayPause!!.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                storiesProgressView?.resume()
                playPause = true
            }
        }

        ivClose?.setOnClickListener {
            storiesProgressView!!.destroy()
//            val i = Intent(this@StoriesActivity, MainActivity::class.java)
//            startActivity(i)
            finish()
        }

        infoTextView?.setOnClickListener {
            val bottomSheet = BottomSheetDialog()
            bottomSheet.show(
                supportFragmentManager,
                "ModalBottomSheet"
            )
            storiesProgressView?.pause()
        }
    }

    override fun onNext() {
//        image?.setImageResource(resources[++counter]);
        Glide.with(this)
            .load(features_images[++counter])
            .into(image!!)
    }

    override fun onPrev() {
        if ((counter - 1) < 0) return;
//        image?.setImageResource(resources[--counter]);
        Glide.with(this)
            .load(features_images[--counter])
            .into(image!!)
    }

    override fun onComplete() {
//        val i = Intent(this@StoriesActivity, MainActivity::class.java)
//        startActivity(i)
        finish()
    }

    override fun onDestroy() {
        // Very important !
        storiesProgressView!!.destroy()
        super.onDestroy()
    }

    companion object {
        var features_images = ArrayList<String>()
        var counter = 0
    }
}