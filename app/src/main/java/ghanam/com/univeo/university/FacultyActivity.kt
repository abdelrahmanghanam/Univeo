package ghanam.com.univeo.university

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ghanam.com.univeo.R
import ghanam.com.univeo.adapters.MajorAdapter
import ghanam.com.univeo.databinding.ActivityFacultyBinding
import ghanam.com.univeo.databinding.ActivityUniversityBinding
import ghanam.com.univeo.singletons.DBReader

class FacultyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFacultyBinding
    private lateinit var majorAdapter: MajorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faculty)
        getSupportActionBar()!!.hide() //hide the title bar

        //preparing expandable card
        binding.dropUpIcon.setOnClickListener {
            binding.dropUpIcon.visibility= View.GONE
            binding.dropDownIcon.visibility= View.VISIBLE
            binding.dropDownCard.visibility= View.GONE
        }
        binding.dropDownIcon.setOnClickListener {
            binding.dropDownIcon.visibility= View.GONE
            binding.dropUpIcon.visibility= View.VISIBLE
            binding.dropDownCard.visibility= View.VISIBLE
        }

        //prepare adapter of recycler view
        val currentFac= DBReader.currentFaculty
        if (currentFac!=null) {
            majorAdapter = MajorAdapter(currentFac["majors"] as MutableList<String>)
            binding.majorRecycler.adapter = majorAdapter
            binding.majorRecycler.layoutManager = LinearLayoutManager(applicationContext)
            binding.fees.text=currentFac["fees"].toString()
            binding.duration.text= currentFac["duration"].toString()
            binding.facultyName.text=currentFac["name"] as String
            binding.universityName.text= DBReader.currentUni!!.name
            Picasso.with(applicationContext).load(DBReader.currentUni!!.logoURL).into(binding.logoImage)
        }


        binding.backButton.setOnClickListener {
            val intent = Intent(this, UniversityActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}