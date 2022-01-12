package ghanam.com.univeo.university

import android.bluetooth.BluetoothClass
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.FacultyAdapter
import ghanam.com.univeo.adapters.MajorAdapter
import ghanam.com.univeo.databinding.FragmentFacultyBinding
import ghanam.com.univeo.databinding.FragmentUniversityBinding
import ghanam.com.univeo.dataclasses.FacultyGeneral

class FacultyFragment : Fragment() {

    lateinit var binding: FragmentFacultyBinding
    private lateinit var majorAdapter: MajorAdapter
    private val majors = mutableListOf(
        "Science",
        "video game development",
        "hhaahaahhaha hahah jhhjhjhjj jhjhhjhj hjhj mnmnnm mnmnm mnmnm m,,nmmn"
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFacultyBinding.inflate(inflater, container, false)

        //preparing expandable card
        binding.dropUpIcon.setOnClickListener {
            binding.dropUpIcon.visibility=View.GONE
            binding.dropDownIcon.visibility=View.VISIBLE
            binding.dropDownCard.visibility=View.GONE
        }
        binding.dropDownIcon.setOnClickListener {
            binding.dropDownIcon.visibility=View.GONE
            binding.dropUpIcon.visibility=View.VISIBLE
            binding.dropDownCard.visibility=View.VISIBLE
        }

        //prepare adapter of recycler view
        majorAdapter = MajorAdapter(majors)
        binding.majorRecycler.adapter=majorAdapter
        binding.majorRecycler.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}