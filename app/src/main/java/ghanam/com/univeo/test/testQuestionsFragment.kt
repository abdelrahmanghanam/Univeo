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


class TestQuestionsFragment : Fragment() {
    lateinit var binding: FragmentTestQuestionsBinding
    private lateinit var testAdapter: QuizAdapter
    private val questions = mutableListOf(
        Quiz("Where is Egypt","Up","down","no Idea"),
        Quiz("Where is Egypt","Up","down","no Idea"),
        Quiz("Where is Egypt","Up","down","no Idea"),
        Quiz("Where is Egypt","Up","down","no Idea")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestQuestionsBinding.inflate(inflater, container, false)

        //test adapter maker
        testAdapter = QuizAdapter(questions)
        binding.recyclerView.adapter=testAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.submitButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_testQuestionsFragment_to_testResultFragment)
        }


        return binding.root
    }


}