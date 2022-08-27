package io.ak.pesgm.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.ak.pesgm.R


class BottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(
            R.layout.bottom_sheet_layout,
            container, false
        )
        val algo_button = v.findViewById<Button>(R.id.algo_button)
        val course_button = v.findViewById<Button>(R.id.course_button)
        algo_button.setOnClickListener {
            Toast.makeText(
                activity,
                "Algorithm Shared", Toast.LENGTH_SHORT
            )
                .show()
            dismiss()
        }
        course_button.setOnClickListener {
            Toast.makeText(
                activity,
                "Course Shared", Toast.LENGTH_SHORT
            )
                .show()
            dismiss()
        }
        return v
    }
}