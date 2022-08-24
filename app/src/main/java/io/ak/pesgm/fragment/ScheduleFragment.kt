package io.ak.pesgm.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.ak.pesgm.adapter.ScheduleAdapter
import io.ak.pesgm.app.SessionManager
import io.ak.pesgm.databinding.FragmentScheduleBinding
import io.ak.pesgm.model.Schedule
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class ScheduleFragment : Fragment() {


    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    val TAG = "ScheduleFragment"

    var scheduleAdapter: ScheduleAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val view = binding.root

        linearLayoutManager = LinearLayoutManager(activity)
        binding.rvShedule.layoutManager = linearLayoutManager

        var scheduleItem = getSchedule()
        scheduleAdapter = ScheduleAdapter(scheduleItem, view.context)
        binding.rvShedule.adapter = scheduleAdapter

        return view
    }

    private fun getSchedule(): MutableList<Schedule> {
        val db = Firebase.firestore
        val scheduleArray = mutableListOf<Schedule>()

        db.collection("pesgm_schedule/2022/2022")
            .orderBy("date")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val schedule = Schedule()
                    schedule.date=document.data.get("date") as Timestamp?
//                    schedule.day=document.data.get("day") as String?
                    schedule.en_info=document.data.get("en_info") as String?
                    schedule.mr_info=document.data.get("mr_info") as String?
                    scheduleArray.add(schedule)
                }
                scheduleAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return scheduleArray
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}