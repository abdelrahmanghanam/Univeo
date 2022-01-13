package ghanam.com.univeo.university

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ghanam.com.univeo.databinding.FragmentUniversityBinding
import ghanam.com.univeo.home.HomePageActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ghanam.com.univeo.adapters.FacultyAdapter
import ghanam.com.univeo.dataclasses.FacultyGeneral


class UniversityFragment : Fragment() {

    lateinit var binding: FragmentUniversityBinding
    private lateinit var facultiesAdapter: FacultyAdapter
    private val faculties = mutableListOf(
        FacultyGeneral("4","Engineering"),
        FacultyGeneral("4","Science"),
        FacultyGeneral("4","Business"),

    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUniversityBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {
            val intent = Intent(getActivity(),  HomePageActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }
        val longtude="30.086616"
        val latitude="31.648787"
        val mapUrl="https://www.google.com/maps?q=$longtude,$latitude"
        binding.map.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            startActivity(browserIntent)
        }


        //news adapter maker
        facultiesAdapter = FacultyAdapter(faculties)
        binding.facultiesRecycler.adapter=facultiesAdapter
        binding.facultiesRecycler.layoutManager = LinearLayoutManager(context)


        return binding.root
    }

}