package ghanam.com.univeo.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ghanam.com.univeo.databinding.FragmentLoginBinding
import ghanam.com.univeo.home.HomePageActivity


class LoginFragment : Fragment() {


    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signupFragment)
//            Log.w("errr",binding.loginEditText.editText!!.text.toString())
            val intent = Intent(getActivity(),  HomePageActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }

        return binding.root
    }


}