package ghanam.com.univeo.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.FragmentSignupBinding
import ghanam.com.univeo.extensions.GeneralExt.showSnack
import ghanam.com.univeo.extensions.GeneralExt.toast
import ghanam.com.univeo.home.HomePageActivity
import ghanam.com.univeo.singletons.CurrentUser


class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var createAccountInputsArray: Array<TextInputLayout>
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser: FirebaseUser? = null
    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = Firebase.firestore
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        //preparing the combobox
        val items = listOf("Cairo", "Alexandria")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.cityMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        createAccountInputsArray = arrayOf(binding.firstNameEditText, binding.lastNameEditText, binding.emailEditText
        ,binding.password,binding.confirmPassword,binding.cityMenu)
        binding.signUpButton.setOnClickListener {
            signUp()
        }
        binding.logIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_loginFragment)
        }
        binding.termsAndCondition.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext()).setMessage(resources.getString(R.string.long_message))
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    binding.agreeCheckBox.isChecked=false
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    binding.agreeCheckBox.isChecked=true
                }
                .show()
        }
        return binding.root
    }

    private fun signUp() {
        if (validate())
        {
            userEmail = binding.emailEditText.editText!!.text.toString().trim()
            userPassword = binding.password.editText!!.text.toString().trim()

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Account created")

                        firebaseUser = firebaseAuth.currentUser
                        val userId:String=firebaseAuth.currentUser!!.uid

                        val user = hashMapOf(
                            "user_id" to userId,
                            "first_name" to binding.firstNameEditText.editText!!.text.toString(),
                            "last_name" to binding.lastNameEditText.editText!!.text.toString(),
                            "city" to binding.cityMenu.editText!!.text.toString(),
                            "email" to userEmail
                        )

                        Log.w("tag error",user.toString())
                        db.collection("users").document(userId).set(user).addOnSuccessListener { documentReference ->
                            Log.d("Success", "DocumentSnapshot added with ID: ${userId}")
                        }.addOnFailureListener { e ->
                                Log.w("failure", "Error adding document", e)
                            }


                        CurrentUser.firstName = user["first_name"].toString().replaceFirstChar(Char::titlecase)
                        CurrentUser.lastName =user["last_name"].toString().replaceFirstChar(Char::titlecase)
                        CurrentUser.city =user["city"].toString().replaceFirstChar(Char::titlecase)
                        val intent = Intent(activity, HomePageActivity::class.java)
                        requireActivity().finish()
                        startActivity(intent)                    } else {
                        toast("Failed to create user: "+task.exception.toString())
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

        if (!binding.agreeCheckBox.isChecked){
            binding.agreeCheckBox.error="You need to agree first"
            return false
        }else{
            binding.agreeCheckBox.error=null
        }


        return true

    }

    private fun isEmpty():Boolean{
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

    private fun passwordMatching():Boolean{
            return binding.password.editText!!.text.toString()==binding.confirmPassword.editText!!.text.toString()
    }


}