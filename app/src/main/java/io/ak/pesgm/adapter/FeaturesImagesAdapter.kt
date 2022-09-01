package io.ak.pesgm.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.ak.pesgm.R
import io.ak.pesgm.app.SessionManager
import io.ak.pesgm.interfaces.RecyclerviewOnClickListener
import io.ak.pesgm.model.Collection
import io.ak.pesgm.utils.BottomSheetDialog


class FeaturesImagesAdapter(
    private val listener: RecyclerviewOnClickListener,
    private val featuresImageList: ArrayList<String>,
    private val context: Context
) : RecyclerView.Adapter<FeaturesImagesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCollection: ImageView

        init {
            ivCollection = view.findViewById(R.id.iv_collection)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.features_image_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(featuresImageList.get(position))
            .into(viewHolder.ivCollection)

        viewHolder.itemView.setOnClickListener{
            this.listener.recyclerviewClick(position)
        }
    }

    override fun getItemCount(): Int  {
        return featuresImageList.size
    }
}