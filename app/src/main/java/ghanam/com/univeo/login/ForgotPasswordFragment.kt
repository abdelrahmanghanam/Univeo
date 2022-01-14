package ghanam.com.univeo.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentForgotPasswordBinding

import ghanam.com.univeo.extensions.GeneralExt.toast


class ForgotPasswordFragment : Fragment() {


    lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentForgotPasswordBinding.inflate(inflater, container, false)

        binding.resetButton.setOnClickListener {
            sendEmail()
        }
        binding.backButton.setOnClickListener {
             Navigation.findNavController(it).navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
        return binding.root
    }

    private fun isEmpty():Boolean{
        if (binding.emailEditText.editText!!.text.toString().trim().isEmpty()) {
            binding.emailEditText.error = "${binding.emailEditText.hint} is required"
            binding.emailEditText.isErrorEnabled=true
            return true
        }
        else{
            // Clear error text
            binding.emailEditText.error = null
            binding.emailEditText.isErrorEnabled=false
        }
        return false

    }

    private fun sendEmail(){
        if (!isEmpty()){
            val userEmail=binding.emailEditText.editText!!.text.toString().trim()
            binding.messageText.visibility=View.GONE
            binding.progressBar.visibility=View.VISIBLE
            FirebaseAuth.getInstance().sendPasswordResetEmail(userEmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Email with reset link sent successfully")
                    }else{
                        toast("Failed to send reset link: "+task.exception.toString())
                        binding.messageText.text=task.exception.toString()
                        binding.messageText.visibility=View.VISIBLE
                    }
                    binding.progressBar.visibility=View.GONE
                }
        }
    }


}