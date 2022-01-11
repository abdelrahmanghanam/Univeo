package ghanam.com.univeo.login

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.ActivityLoginBinding
import ghanam.com.univeo.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        getSupportActionBar()!!.hide(); //hide the title bar
    }
}