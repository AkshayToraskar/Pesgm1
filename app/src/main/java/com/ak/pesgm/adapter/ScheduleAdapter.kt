package com.ak.pesgm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ak.pesgm.R
import com.ak.pesgm.model.Schedule

class ScheduleAdapter(
    private val scheduleList: MutableList<Schedule>,
    private val context: Context
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView
        val tvDay: TextView
        val tvMrInfo: TextView
//        val tvEnInfo: TextView

        init {
            tvDate = view.findViewById(R.id.tv_date)
            tvDay = view.findViewById(R.id.tv_day)
            tvMrInfo = view.findViewById(R.id.tv_mr_info)
//            tvEnInfo = view.findViewById(R.id.tv_en_info)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.schedule_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvDay.text = scheduleList[position].day
        viewHolder.tvDate.text = scheduleList[position].date
        viewHolder.tvMrInfo.text = scheduleList[position].mr_info
//        viewHolder.tvEnInfo.text = scheduleList[position].en_info
    }

    override fun getItemCount(): Int  {
        return scheduleList.size
    }

}