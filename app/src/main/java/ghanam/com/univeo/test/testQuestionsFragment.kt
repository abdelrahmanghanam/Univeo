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
import ghanam.com.univeo.singletons.DBReader


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
            if (checkAllAnswered()==true) {
                val chosen = chooseBestMatching()
                var maxFees: Int
                when(answers[2]){
                    1-> maxFees=4000
                    2-> maxFees=8000
                    3-> maxFees=20000
                    else->maxFees=20000
                }
                showSnack(maxFees.toString(),chosen,it)
                DBReader.uniMatching=chooseFromAvailableUniversities(chosen,maxFees)
                Navigation.findNavController(it).navigate(R.id.action_testQuestionsFragment_to_testResultFragment)

            }
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


    //some AI
    private fun chooseBestMatching():String{
        if (answers[0]==2)
            return "medicine"
        if (answers[0]==3)
            return "business"
        if (answers[0]==1)
            if (answers[1]==1)
                return "computer science"
            if (answers[1]==2)
                return "business"
            if (answers[1]==3)
                return "engineering"
        return ""
    }

    fun chooseFromAvailableUniversities(facultyName:String, maxFees: Int):HashMap<String,String>?{
        val universities = DBReader.universitiesList
        var bestUniversity :HashMap<String, String>? = null
        for (item in universities){

            for (element in item.faculties) {
                if (element["name"]!!.toString().lowercase()==facultyName.lowercase()) {
                    val universityMatch = HashMap<String, String>()
                    universityMatch["fac_name"] = "Faculty of " + element["name"]!!.toString()
                    universityMatch["uni_name"] = item.name
                    universityMatch["fees"] = element["fees"]!!.toString()
                    universityMatch["city"] = item.city
                    universityMatch["duration"] = element["duration"]!!.toString()
                    universityMatch["rank"] = item.rank
                    universityMatch["majors"] = "Majors: " + element["majors"]!!.toString()
                    universityMatch["image_url"] = item.imageURL

                    if (element["fees"]!!.toString().toInt() <= maxFees) {
                        universityMatch["matching"] = "100"
                    } else {
                        universityMatch["matching"] = "75"
                    }

                    if (bestUniversity != null) {
                        if (universityMatch["matching"]!!.toInt() > bestUniversity["matching"]!!.toInt())
                            bestUniversity = universityMatch
                    }else{
                        bestUniversity=universityMatch
                    }


                }
            }

        }
        return bestUniversity
    }


}