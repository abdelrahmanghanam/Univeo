package ghanam.com.univeo.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.UniversitiesAdapter
import ghanam.com.univeo.databinding.ActivitySearchBinding
import ghanam.com.univeo.dataclasses.UniversityGeneral
import ghanam.com.univeo.home.HomePageActivity
import ghanam.com.univeo.singletons.CurrentUser
import ghanam.com.univeo.singletons.DBReader

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    private lateinit var universitiesAdapter: UniversitiesAdapter
    val universities= DBReader.universitiesList
    val searchedUniversities:MutableList<UniversityGeneral> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        getSupportActionBar()!!.hide()

        binding.backButton.setOnClickListener {
            val intent = Intent(this,  HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.searchButton.setOnClickListener{
            val locationOn=binding.switchLocation.isChecked
            val budgetString=binding.feesEditText.editText!!.text.toString().trim()

            if (locationOn and !budgetString.isEmpty()){
                val budgetNumeric=budgetString.toInt()
                searchUsingPlaceAndBudget(CurrentUser.city,budgetNumeric)
            }else if (locationOn){
                searchUsingPlace(CurrentUser.city)
            }else if (!budgetString.isEmpty()){
                val budgetNumeric=budgetString.toInt()
                searchUsingBudget(budgetNumeric)
            }else{
                putAll()
            }
        }

        //university adapter maker
        universitiesAdapter = UniversitiesAdapter(searchedUniversities)
        binding.universitiesRecycler.adapter=universitiesAdapter
        binding.universitiesRecycler.layoutManager = LinearLayoutManager(applicationContext)
    }


    private fun searchUsingPlaceAndBudget(city: String,maxBudget: Int){

        universitiesAdapter.clearList()
        binding.progressBar.visibility= View.VISIBLE

        for (item in universities){
            var found=false
            for (element in item.faculties){
                if (element["fees"]!!.toString().toInt()<=maxBudget) {
                    found = true
                    break
                }
            }
            if (found && item.city.lowercase()==city.lowercase())
                universitiesAdapter.addUniversity(UniversityGeneral(item.name,item.rank,item.city,item.id,item.imageURL))

        }

        binding.progressBar.visibility=View.GONE
        if (universitiesAdapter.itemCount==0){
            binding.noResultsText.visibility=View.VISIBLE
        }else{
            binding.noResultsText.visibility=View.GONE
        }
    }

    private fun searchUsingBudget(maxBudget: Int){

        universitiesAdapter.clearList()
        binding.progressBar.visibility= View.VISIBLE

        for (item in universities){
            var found=false
            for (element in item.faculties){
                if (element["fees"]!!.toString().toInt()<=maxBudget) {
                    found = true
                    break
                }
            }
            if (found)
                universitiesAdapter.addUniversity(UniversityGeneral(item.name,item.rank,item.city,item.id,item.imageURL))

        }


        binding.progressBar.visibility=View.GONE
        if (universitiesAdapter.itemCount==0){
            binding.noResultsText.visibility=View.VISIBLE
        }else{
            binding.noResultsText.visibility=View.GONE
        }
    }

    private fun searchUsingPlace(city:String){

        universitiesAdapter.clearList()
        binding.progressBar.visibility= View.VISIBLE

        for (item in universities){
            if (item.city.lowercase()==city.lowercase())
                universitiesAdapter.addUniversity(UniversityGeneral(item.name,item.rank,item.city,item.id,item.imageURL))
        }

        binding.progressBar.visibility=View.GONE
        if (universitiesAdapter.itemCount==0){
            binding.noResultsText.visibility=View.VISIBLE
        }else{
            binding.noResultsText.visibility=View.GONE
        }
    }

    private fun putAll(){
        universitiesAdapter.clearList()
        binding.progressBar.visibility= View.VISIBLE
        for (item in universities){
            universitiesAdapter.addUniversity(UniversityGeneral(item.name,item.rank,item.city,item.id,item.imageURL))
        }

        binding.progressBar.visibility=View.GONE
        if (universitiesAdapter.itemCount==0){
            binding.noResultsText.visibility=View.VISIBLE
        }else{
            binding.noResultsText.visibility=View.GONE
        }
    }
}