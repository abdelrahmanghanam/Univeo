package ghanam.com.univeo

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import ghanam.com.univeo.databinding.ActivityMainBinding
import ghanam.com.univeo.extensions.AnimationExt.clockwise
import ghanam.com.univeo.extensions.AnimationExt.fade
import ghanam.com.univeo.extensions.AnimationExt.slideIn
import ghanam.com.univeo.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getSupportActionBar()!!.hide(); //hide the title bar
        binding.logoDown.slideIn(2000L,0L)
        binding.logoTop.clockwise(1500L,0L)
        move()

    }



    fun move(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,  LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}