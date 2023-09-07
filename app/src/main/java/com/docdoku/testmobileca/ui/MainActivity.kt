package com.docdoku.testmobileca.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.docdoku.testmobileca.R
import com.docdoku.testmobileca.databinding.ActivityMainBinding
import com.docdoku.testmobileca.ui.details.AccountDetailsFragment.Companion.ARG_ACCOUNT
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/**
 * This is the main activity of the application.
 *
 * It contains the navigation graph and the bottom navigation view.
 * it is responsible to display all the fragments of the application.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var appBarConfiguration:
            AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_details){

                binding.toolbar.setTitleTextColor(resources.getColor(R.color.toolbar_color, null))

                binding.root.postDelayed(
                    {
                        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back)
                    },
                    0
                )
            }

            else{
                binding.toolbar.setTitleTextColor(resources.getColor(R.color.black, null))
            }
        }

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_accounts, R.id.nav_simulation, R.id.nav_play))

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            ?.setupWithNavController(navController)

        viewModel.event.receive(this) { event ->
            when(event) {
                is MainViewModel.Event.ShowDetails -> {
                    navController.navigate(
                        R.id.action_nav_accounts_to_nav_details,
                        bundleOf(ARG_ACCOUNT to event.bankAccount)
                    )
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.popBackStack()
                || super.onSupportNavigateUp()
    }
}