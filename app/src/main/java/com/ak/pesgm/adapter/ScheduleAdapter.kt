package com.ak.pesgm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ak.pesgm.R
import com.ak.pesgm.model.Schedule
import com.github.vipulasri.timelineview.TimelineView

class ScheduleAdapter(
    private val scheduleList: MutableList<Schedule>,
    private val context: Context
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView
        val tvDay: TextView
        val tvMrInfo: TextView
        val timelineView: TimelineView

        init {
            tvDate = view.findViewById(R.id.tv_date)
            tvDay = view.findViewById(R.id.tv_day)
            tvMrInfo = view.findViewById(R.id.tv_mr_info)
            timelineView = view.findViewById(R.id.timeline)
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

        viewHolder.timelineView.initLine(position)

    }

    override fun getItemCount(): Int  {
        return scheduleList.size
    }

}