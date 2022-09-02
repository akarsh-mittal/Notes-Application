package com.example.notes

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

//        navController=findNavController(R.id.fragmentContainerView)
//        setupActionBarWithNavController(navController) //setting nav controller with action bar (-> , <-)

        //Creating User Profile CRUD - Budget Application with Room Library & MVVM Architecture Android YT
        val navHostFragment = supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container)

        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }


        //bottomNavBar not working?
//        binding.bottomNavBar.setupWithNavController(navController)


    }

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