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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentLoginBinding
import ghanam.com.univeo.extensions.GeneralExt.toast
import ghanam.com.univeo.home.HomePageActivity
import ghanam.com.univeo.singletons.CurrentUser


class LoginFragment : Fragment() {


    lateinit var binding: FragmentLoginBinding
    lateinit var signInInputsArray: Array<TextInputLayout>
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        signInInputsArray = arrayOf(binding.loginEditText, binding.passwordEditText)

        binding.loginButton.setOnClickListener {

            signIn()
        }

        binding.signUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.forgotPasswordLink.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        return binding.root
    }

    fun signIn(){
        if (isEmpty())
            return
        binding.progressBar.visibility=View.VISIBLE
        val signInEmail = binding.loginEditText.editText!!.text.toString().trim()
        val signInPassword = binding.passwordEditText.editText!!.text.toString().trim()
        firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
            .addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    toast("Signed in successfully")
                    firebaseUser = firebaseAuth.currentUser
                    val userId:String=firebaseAuth.currentUser!!.uid
                    val db = Firebase.firestore
                    db.collection("users").document(userId).get().addOnSuccessListener {
                        val data= it.data
                        CurrentUser.firstName = data!!["first_name"].toString().replaceFirstChar(Char::titlecase)
                        CurrentUser.lastName =data["last_name"].toString().replaceFirstChar(Char::titlecase)
                        CurrentUser.city =data["city"].toString().replaceFirstChar(Char::titlecase)
                        val intent = Intent(activity, HomePageActivity::class.java)
                        requireActivity().finish()
                        startActivity(intent)
                    }.addOnFailureListener {
                        toast("not able to connect to database")
                        binding.progressBar.visibility=View.GONE
                    }

                } else {
                    toast("Failed to create user: "+signIn.exception.toString())
                    binding.progressBar.visibility=View.GONE
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