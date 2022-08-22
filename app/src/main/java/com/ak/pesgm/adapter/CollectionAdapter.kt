package com.ak.pesgm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ak.pesgm.R
import com.ak.pesgm.app.SessionManager
import com.ak.pesgm.fragment.HomeFragment
import com.ak.pesgm.model.Collection
import com.bumptech.glide.Glide


class CollectionAdapter(
    private val collectionList: MutableList<Collection>,
    private val context: Context
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var tv_year: TextView
    private lateinit var iv_collection: ImageView

    override fun getCount(): Int {
        return collectionList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, collectionView: View?, parent: ViewGroup?): View? {
        var collectionView = collectionView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (collectionView == null) {
            collectionView = layoutInflater!!.inflate(R.layout.collection_item, null)
        }
        iv_collection = collectionView!!.findViewById(R.id.iv_collection)
        tv_year = collectionView!!.findViewById(R.id.tv_year)


        val sessionManager = SessionManager(context)
        if (sessionManager.language == "mr") {
            tv_year.setTypeface(context.resources.getFont(R.font.amsmanthan))
            tv_year.setTextSize(21f)
        } else if (sessionManager.language == "en") {
            tv_year.setTypeface(context.resources.getFont(R.font.josefinsansregular))
            tv_year.setTextSize(19f)
        }

        Glide.with(context)
            .load(collectionList.get(position).img_thumb)
            .into(iv_collection);
//        iv_collection.setImageURI(courseList.get(position).img_path)
        tv_year.setText(collectionList.get(position).en_year)
        return collectionView
    }
}