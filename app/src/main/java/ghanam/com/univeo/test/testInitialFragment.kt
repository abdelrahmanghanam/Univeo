package ghanam.com.univeo.test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentTestInitialBinding
import ghanam.com.univeo.home.HomePageActivity

class testInitialFragment : Fragment() {
    lateinit var binding: FragmentTestInitialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestInitialBinding.inflate(inflater, container, false)
        binding.getStartedButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_testInitialFragment_to_testQuestionsFragment)
        }
        binding.backButton.setOnClickListener {

            val intent = Intent(getActivity(),  HomePageActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }



        return binding.root
    }

}