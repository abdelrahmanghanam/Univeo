package ghanam.com.univeo.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.ActivityLoginBinding
import ghanam.com.univeo.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)
        getSupportActionBar()!!.hide()
    }
}