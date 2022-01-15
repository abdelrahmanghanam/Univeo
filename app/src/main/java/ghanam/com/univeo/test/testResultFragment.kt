package ghanam.com.univeo.test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import ghanam.com.univeo.databinding.FragmentTestResultBinding
import ghanam.com.univeo.home.HomePageActivity
import ghanam.com.univeo.singletons.DBReader

class testResultFragment : Fragment() {

    lateinit var binding: FragmentTestResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestResultBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {

            val intent = Intent(activity,  HomePageActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }

        if (DBReader.uniMatching==null){
            binding.wholeLayout.visibility=View.GONE
            binding.failText.visibility=View.VISIBLE
        }else{
            val universityMatch=DBReader.uniMatching
            binding.facultyTitle.text=universityMatch!!["fac_name"]
            binding.uniTitle.text=universityMatch["uni_name"]
            binding.feesText.text=universityMatch["fees"]
            binding.locationText.text=universityMatch["city"]
            binding.yearsText.text=universityMatch["duration"]
            binding.rankText.text=universityMatch["rank"]
            binding.majorTitle.text=universityMatch["majors"]
            binding.matchingPercent.text=universityMatch["matching"]+" %"
            Picasso.with(context).load(universityMatch["image_url"]).into(binding.uniImage)



        }





        return binding.root
    }


}