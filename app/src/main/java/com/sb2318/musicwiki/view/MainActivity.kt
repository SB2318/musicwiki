package com.sb2318.musicwiki.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sb2318.musicwiki.R
import com.sb2318.musicwiki.databinding.ActivityMainBinding
import com.sb2318.musicwiki.viewModel.GenericViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    //Instantiate a ViewModel Object which extends android viewmodel class from an activity
    val viewModel by lazy {
        ViewModelProvider(this)[GenericViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // create nav controller
        navController= navHostFragment.navController
    }
}