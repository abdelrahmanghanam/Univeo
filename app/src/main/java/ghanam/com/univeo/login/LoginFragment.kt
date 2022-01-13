package ghanam.com.univeo.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentLoginBinding
import ghanam.com.univeo.extensions.GeneralExt.toast
import ghanam.com.univeo.home.HomePageActivity


class LoginFragment : Fragment() {


    lateinit var binding: FragmentLoginBinding
    lateinit var signInInputsArray: Array<TextInputLayout>
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        signInInputsArray = arrayOf(binding.loginEditText, binding.passwordEditText)

        binding.loginButton.setOnClickListener {

            signIn()
        }

        binding.signUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return binding.root
    }

    fun signIn(){
        if (isEmpty())
            return
        val signInEmail = binding.loginEditText.editText!!.text.toString().trim()
        val signInPassword = binding.passwordEditText.editText!!.text.toString().trim()
        firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
            .addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    toast("Signed in")
                    val intent = Intent(getActivity(), HomePageActivity::class.java)
                    requireActivity().finish()
                    startActivity(intent)
                } else {
                    toast("Sign in failed")
                }
            }
    }

    fun isEmpty():Boolean{
        var empty=false
        signInInputsArray.forEach { input ->
            if (input.editText!!.text.toString().trim().isEmpty()) {
                input.error = "${input.hint} is required"
                input.isErrorEnabled=true
                empty=true
            }
            else{
                // Clear error text
                input.error = null
                input.isErrorEnabled=false
            }
        }
        return empty
    }

}