package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notes.databinding.ActivityMainBinding

// we will use navigation components to go between fragments
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
//    lateinit var binding:MainActivityBindinf
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val intent = Intent(this, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)

//        navController=findNavController(R.id.fragmentContainerView)
//        setupActionBarWithNavController(navController) //setting nav controller with action bar (-> , <-)

        //Creating User Profile CRUD - Budget Application with Room Library & MVVM Architecture Android YT
        val navHostFragment = supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container)

        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }


        //bottomNavBar not working?
//        binding.bottomNavBar.setupWithNavController(navController)

//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


    }

//    override fun onBackPressed() {
//        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
//        val intent = Intent(Intent.ACTION_MAIN)
//        intent.addCategory(Intent.CATEGORY_HOME)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
//    }

//below two functions re-starting the app instead of going to profile fragment..note->no exception is there though
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.option_menu,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }



    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }



}