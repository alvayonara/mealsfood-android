package com.alvayonara.mealsfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alvayonara.mealsfood.core.utils.Helper.setDefaultStatusBarColor
import com.alvayonara.mealsfood.core.utils.Helper.setLightStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initBottomNavBar()
    }

    private fun initToolbar() {
        setDefaultStatusBarColor(this)
        setLightStatusBar(this)
    }

    private fun initBottomNavBar() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }
}