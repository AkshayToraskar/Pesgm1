package com.ak.pesgm.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ak.pesgm.R
import com.ak.pesgm.adapter.CollectionAdapter
import com.ak.pesgm.databinding.FragmentHomeBinding
import com.ak.pesgm.model.Collection
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val TAG = "HomeFragment"
    var collectionAdapter: CollectionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        var collectionItem = getCollection()
        collectionAdapter = CollectionAdapter(collectionItem, view.context)
        binding.collectionGallery.adapter = collectionAdapter

        return view
    }

    private fun getCollection(): MutableList<Collection> {
        val db = Firebase.firestore
        val collArray = mutableListOf<Collection>()

        db.collection("pesgm_collection")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val coll = Collection()
                    coll.en_info= document.data.get("en_info") as String?
                    coll.en_year=document.data.get("en_year") as String?
                    coll.img_path=document.data.get("img_path") as String?
                    coll.img_thumb=document.data.get("img_thumb") as String?
                    coll.mr_info=document.data.get("mr_info") as String?
                    coll.mr_year=document.data.get("mr_year") as String?
                    collArray.add(coll)
                }
                collectionAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return collArray
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}