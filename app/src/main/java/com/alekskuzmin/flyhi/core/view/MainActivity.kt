package com.alekskuzmin.flyhi.core.view

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alekskuzmin.flyhi.R
import com.alekskuzmin.flyhi.core.state.StateHolder
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val stateHolder: StateHolder by inject()
    private lateinit var navController: NavController

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
    }

    fun setupToolbar(toolbar: Toolbar, action: ActionBar.() -> Unit = {}) {
        setSupportActionBar(toolbar)
        val topLevelDestinations = HashSet<Int>()
        //topLevelDestinations.add(R.id.login_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        supportActionBar?.run {
            action()
        }
    }

    override fun onStop() {
        if (isFinishing) {
            stateHolder.resetState()
        }
        super.onStop()
    }
}
