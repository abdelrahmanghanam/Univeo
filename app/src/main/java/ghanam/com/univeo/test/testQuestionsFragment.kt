package ghanam.com.univeo.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.QuizAdapter
import ghanam.com.univeo.databinding.FragmentTestQuestionsBinding
import ghanam.com.univeo.dataclasses.Quiz
import ghanam.com.univeo.extensions.GeneralExt.showSnack


class TestQuestionsFragment : Fragment() {
    lateinit var binding: FragmentTestQuestionsBinding
    private lateinit var testAdapter: QuizAdapter
    private val questions = mutableListOf(
        Quiz("What subject you prefer?","Math","Biology","Neither"),
        Quiz("What do you prefer?","Understand","Memorize","Mixture between them"),
        Quiz("What is your max budget?","4,000","8,000","20,000"),
    )
    private val answers=IntArray(questions.size)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestQuestionsBinding.inflate(inflater, container, false)
        //test adapter maker
        testAdapter = QuizAdapter(questions,answers)
        binding.recyclerView.adapter=testAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.submitButton.setOnClickListener {
            if (checkAllAnswered()==true)
                Navigation.findNavController(it).navigate(R.id.action_testQuestionsFragment_to_testResultFragment)
            else showSnack("Complete all answers","Error",it)
        }


        return binding.root
    }

    private fun checkAllAnswered():Boolean{
        for (element in answers){
            if (element==0)
                return false
        }
        return true
    }


}