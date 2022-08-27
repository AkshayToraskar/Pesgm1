package io.ak.pesgm.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.ak.pesgm.StoriesActivity
import io.ak.pesgm.adapter.CollectionAdapter
import io.ak.pesgm.databinding.FragmentHomeBinding
import io.ak.pesgm.interfaces.RecyclerviewOnClickListener
import io.ak.pesgm.model.Collection


class HomeFragment : Fragment(), RecyclerviewOnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val TAG = "HomeFragment"
    var collectionAdapter: CollectionAdapter? = null
    lateinit var listener: RecyclerviewOnClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        listener = this

        var collectionItem = getCollection()
        val gridLayoutManager = GridLayoutManager(activity,2)
        binding.rvCollectionGallery.layoutManager = gridLayoutManager
        collectionAdapter = CollectionAdapter(listener, collectionItem, view.context)
        binding.rvCollectionGallery.adapter = collectionAdapter

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

    override fun recyclerviewClick(position: Int) {
//        Toast.makeText(context, "asdf$position", Toast.LENGTH_SHORT).show()

        val i = Intent(activity, StoriesActivity::class.java)
        startActivity(i)
    }
}