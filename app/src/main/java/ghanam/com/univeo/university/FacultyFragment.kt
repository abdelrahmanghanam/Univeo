package ghanam.com.univeo.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.MajorAdapter
import ghanam.com.univeo.databinding.FragmentFacultyBinding
import ghanam.com.univeo.singletons.DBReader
import okhttp3.internal.notifyAll


class FacultyFragment : Fragment() {

    lateinit var binding: FragmentFacultyBinding
    private lateinit var majorAdapter: MajorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        val currentFac= DBReader.currentFaculty
        if (currentFac!=null) {
            majorAdapter = MajorAdapter(currentFac["majors"] as MutableList<String>)
            binding.majorRecycler.adapter = majorAdapter
            binding.majorRecycler.layoutManager = LinearLayoutManager(context)
            binding.fees.text=currentFac["fees"].toString()
            binding.duration.text= currentFac["duration"].toString()
            binding.facultyName.text=currentFac["name"] as String
            binding.universityName.text=DBReader.currentUni!!.name
            Picasso.with(context).load(DBReader.currentUni!!.logoURL).into(binding.logoImage)
        }


        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_facultyFragment_to_universityFragment)
        }

        return binding.root
    }
}