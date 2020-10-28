package com.alvayonara.mealsfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alvayonara.mealsfood.R
import com.alvayonara.mealsfood.core.utils.ToolbarConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initBottomNavBar()
    }

    private fun initToolbar() {
        ToolbarConfig.setDefaultStatusBarColor(this)
    }

    private fun initBottomNavBar() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }
}