package ghanam.com.univeo.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.ActivityHomePageBinding
import ghanam.com.univeo.databinding.NavHeaderHomePageBinding
import ghanam.com.univeo.extensions.GeneralExt.toast
import ghanam.com.univeo.login.LoginActivity
import ghanam.com.univeo.test.TestActivity
import ghanam.com.univeo.university.UniversityActivity

class HomePageActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomePageBinding
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHomePage.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home_page)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_test, R.id.nav_about, R.id.nav_out
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener{ it: MenuItem ->
            when (it.itemId) {
                R.id.nav_test -> goToTestActivity()
                R.id.nav_about->goToUniversityActivity()
                R.id.nav_out-> signOut()
                else -> {
                    true
                }
            }
        }
        binding.appBarHomePage.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.nav_search -> doThat() //your code
            }
            true
        }



       //acount maker
        val header=binding.navView.getHeaderView(0)
        val headerBinding: NavHeaderHomePageBinding = NavHeaderHomePageBinding.bind(header)
//        Log.w("Tag", headerBinding.firstCTxt.text.toString())




    }

    private fun signOut(): Boolean {
        firebaseAuth.signOut()
        toast("Signed out")
        val intent = Intent(this,  LoginActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    private fun doThat(): Boolean {
        val intent = Intent(this,  LoginActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    private fun goToTestActivity(): Boolean{
        val intent = Intent(this,  TestActivity::class.java)
        finish()
        startActivity(intent)
        return true
    }


    private fun goToUniversityActivity(): Boolean{
        val intent = Intent(this,  UniversityActivity::class.java)
        finish()
        startActivity(intent)
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_page, menu)
        return true
    }





    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_page)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}