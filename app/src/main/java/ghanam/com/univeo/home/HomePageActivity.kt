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
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import ghanam.com.univeo.R
import ghanam.com.univeo.databinding.ActivityHomePageBinding
import ghanam.com.univeo.databinding.NavHeaderHomePageBinding
import ghanam.com.univeo.extensions.GeneralExt.toast
import ghanam.com.univeo.login.LoginActivity
import ghanam.com.univeo.search.SearchActivity
import ghanam.com.univeo.singletons.CurrentUser
import ghanam.com.univeo.test.TestActivity

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
                R.id.nav_home->goToHomeFragment(navController)
                R.id.nav_test -> goToTestActivity()
                R.id.nav_about->goToAboutFragment(navController)
                R.id.nav_out-> signOut()
                else -> {
                    true
                }
            }
        }
        binding.appBarHomePage.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.nav_search -> goToSearch() //your code
            }
            true
        }



       //acount maker
        val header=binding.navView.getHeaderView(0)
        val headerBinding: NavHeaderHomePageBinding = NavHeaderHomePageBinding.bind(header)
        headerBinding.firstCTxt.text= CurrentUser.firstName[0].toString()
        headerBinding.nameTxt.text=CurrentUser.firstName+" "+CurrentUser.lastName
        headerBinding.city.text=CurrentUser.city





    }

    private fun goToSearch():Boolean {
        val intent = Intent(this,  SearchActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    private fun goToHomeFragment(navController: NavController): Boolean {
        if (navController.currentDestination!!.id!=R.id.nav_home) {
            navController.navigate(R.id.action_nav_about_to_nav_home)
        }
        return true
    }


    private fun signOut(): Boolean {
        firebaseAuth.signOut()
        toast("Signed out")
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


    private fun goToAboutFragment(navController: NavController): Boolean{
        if (navController.currentDestination!!.id!=R.id.nav_about) {
            navController.navigate(R.id.action_nav_home_to_nav_about)
        }
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