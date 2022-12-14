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


class CollectionAdapter(
    private val listener: RecyclerviewOnClickListener,
    private val collectionList: MutableList<Collection>,
    private val context: Context
) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvYear: TextView
        val ivCollection: ImageView

        init {
            tvYear = view.findViewById(R.id.tv_year)
            ivCollection = view.findViewById(R.id.iv_collection)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.collection_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val sessionManager = SessionManager(context)
        var year: String = "0"
        if (sessionManager.language == "mr") {
            year = collectionList.get(position).mr_year.toString()
        } else if (sessionManager.language == "en") {
            year = collectionList.get(position).en_year.toString()
        }

        viewHolder.tvYear.text = year

        Glide.with(context)
            .load(collectionList.get(position).img_thumb)
            .into(viewHolder.ivCollection)

        viewHolder.itemView.setOnClickListener{
            this.listener.recyclerviewClick(position)
        }
    }

    override fun getItemCount(): Int  {
        return collectionList.size
    }
}