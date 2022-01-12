package ghanam.com.univeo.test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentLoginBinding
import ghanam.com.univeo.databinding.FragmentTestResultBinding
import ghanam.com.univeo.home.HomePageActivity

class testResultFragment : Fragment() {

    lateinit var binding: FragmentTestResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestResultBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {

            val intent = Intent(getActivity(),  HomePageActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }



        return binding.root
    }


}