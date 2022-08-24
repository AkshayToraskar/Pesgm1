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
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAdapter(
    private val scheduleList: MutableList<Schedule>,
    private val context: Context
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView
        val tvInfo: TextView
        val timelineView: TimelineView

        init {
            tvDate = view.findViewById(R.id.tv_date)
            tvInfo = view.findViewById(R.id.tv_info)
            timelineView = view.findViewById(R.id.timeline)
            timelineView.initLine(viewType);
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.schedule_item, viewGroup, false)
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val timestamp = scheduleList[position].date
        val milliseconds = (timestamp?.seconds ?: 0) * 1000 + (timestamp?.nanoseconds ?: 0) / 1000000
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(milliseconds)
        val date = sdf.format(netDate).toString()

        viewHolder.tvDate.text = date
        viewHolder.tvInfo.text = scheduleList[position].mr_info
    }

    override fun getItemCount(): Int  {
        return scheduleList.size
    }

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

}