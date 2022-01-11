package ghanam.com.univeo.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentLoginBinding
import ghanam.com.univeo.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.signUpButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_loginFragment)
        }
        return binding.root
    }


}