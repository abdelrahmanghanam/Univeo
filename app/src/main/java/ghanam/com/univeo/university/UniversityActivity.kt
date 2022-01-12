package ghanam.com.univeo.university

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.ActivityLoginBinding
import ghanam.com.univeo.databinding.ActivityUniversityBinding

class UniversityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUniversityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_university)
        getSupportActionBar()!!.hide(); //hide the title bar
    }
}