package io.ak.pesgm

import android.content.Intent
import android.os.Bundle
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

    private val resources = arrayOf<String>(
        "https://firebasestorage.googleapis.com/v0/b/pesgm-3a470.appspot.com/o/img_thumb%2Ffd1984.jpg?alt=media&token=0815777b-1b08-4774-839a-359ba7a1beae",
        "https://firebasestorage.googleapis.com/v0/b/pesgm-3a470.appspot.com/o/img_thumb%2Ffd1985.jpg?alt=media&token=a03abdaf-d73a-4e4c-8c5b-db25427c8644",
        "https://firebasestorage.googleapis.com/v0/b/pesgm-3a470.appspot.com/o/img_thumb%2Ffd1986.jpg?alt=media&token=45748b86-9aa9-46c1-b356-ad45fb0ee19d",
        "https://firebasestorage.googleapis.com/v0/b/pesgm-3a470.appspot.com/o/img_thumb%2Ffd1984.jpg?alt=media&token=0815777b-1b08-4774-839a-359ba7a1beae",
        "https://firebasestorage.googleapis.com/v0/b/pesgm-3a470.appspot.com/o/img_thumb%2Ffd1985.jpg?alt=media&token=a03abdaf-d73a-4e4c-8c5b-db25427c8644",
        "https://firebasestorage.googleapis.com/v0/b/pesgm-3a470.appspot.com/o/img_thumb%2Ffd1986.jpg?alt=media&token=45748b86-9aa9-46c1-b356-ad45fb0ee19d"
    )

    var pressTime = 0L
    var limit = 500L

    private var storiesProgressView: StoriesProgressView? = null
    private var infoTextView : TextView ?= null
    private var image: ImageView? = null
    private var ivClose: ImageView? = null
    private var ivPlayPause: ImageView? = null
    private var counter = 0


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

        storiesProgressView?.setStoriesCount(resources.size);
        storiesProgressView?.setStoryDuration(3000L);
        storiesProgressView?.setStoriesListener(this);
        storiesProgressView?.startStories(counter);
        image = findViewById<View>(R.id.image) as ImageView?;

        Glide.with(this)
            .load(resources[counter])
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
            .load(resources[++counter])
            .into(image!!)
    }

    override fun onPrev() {
        if ((counter - 1) < 0) return;
//        image?.setImageResource(resources[--counter]);
        Glide.with(this)
            .load(resources[--counter])
            .into(image!!)
    }

    override fun onComplete() {
        val i = Intent(this@StoriesActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onDestroy() {
        // Very important !
        storiesProgressView!!.destroy()
        super.onDestroy()
    }
}