package com.ak.pesgm.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.ak.pesgm.MainActivity
import com.ak.pesgm.R
import com.ak.pesgm.app.SessionManager
import com.ak.pesgm.databinding.FragmentSettingBinding
import java.util.*


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    val TAG = "SettingFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val view = binding.root

        sessionManager = SessionManager(activity)

        if (sessionManager.language == "mr") {
            binding.rbMarathi.setChecked(true)
            binding.tvLanguage.setTypeface(resources.getFont(R.font.amsmanthan))
            binding.tvLanguage.setTextSize(23F)
            binding.rbMarathi.setTypeface(resources.getFont(R.font.amsmanthan))
        } else if (sessionManager.language == "en") {
            binding.rbEnglish.setChecked(true)
            binding.rbMarathi.setTypeface(resources.getFont(R.font.amsmanthan))
            binding.tvLanguage.setTypeface(resources.getFont(R.font.josefinsansregular))
        }


        binding.rgLang.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_marathi -> {
                    sessionManager.language = "mr"
                    setLocale("mr")
                }
                R.id.rb_english -> {
                    sessionManager.language = "en"
                    setLocale("en")
                }
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLocale(lang: String?) {
        var myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(activity, MainActivity::class.java)
        refresh.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(refresh)
    }
}