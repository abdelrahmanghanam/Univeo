package ghanam.com.univeo.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentSignupBinding
import ghanam.com.univeo.extensions.GeneralExt.showSnack
import ghanam.com.univeo.extensions.GeneralExt.toast
import ghanam.com.univeo.home.HomePageActivity


class SignupFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding
    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<TextInputLayout>
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser: FirebaseUser? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        createAccountInputsArray = arrayOf(binding.firstNameEditText, binding.lastNameEditText, binding.emailEditText
        ,binding.password,binding.confirmPassword)

        binding.signUpButton.setOnClickListener {
            signUp(it)
        }

        binding.logIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_loginFragment)
        }
        return binding.root
    }

    private fun signUp(it: View) {
        if (validate())
        {
            userEmail = binding.emailEditText.editText!!.text.toString().trim()
            userPassword = binding.password.editText!!.text.toString().trim()

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Account created")
                        firebaseUser = firebaseAuth.currentUser
                        Log.w("tag1",firebaseUser!!.uid)

                        firebaseAuth.signOut()
                        Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_loginFragment)
                    } else {
                        toast("Account creation failed")
                    }
                }
        }
    }

    private fun validate():Boolean{
        if (isEmpty()){
            return false
        }
        if (!passwordMatching()) {
            showSnack("Password mismatch", "Error", binding.parentLayout)
            binding.confirmPassword.error= "passwords are not identical"
            return false
        }else
        {
            binding.confirmPassword.error=null
        }

        if (!binding.agreeCheckBox.isEnabled){
            binding.agreeCheckBox.error="You need to agree first"
            return false
        }else{
            binding.agreeCheckBox.error=null
        }


        return true

    }

    fun isEmpty():Boolean{
        var empty=false
        createAccountInputsArray.forEach { input ->
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

    fun passwordMatching():Boolean{
            return binding.password.editText!!.text.toString()==binding.confirmPassword.editText!!.text.toString()
    }


}