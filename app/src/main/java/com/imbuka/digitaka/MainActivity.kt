package com.imbuka.digitaka

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.imbuka.digitaka.collector.Collector
import com.imbuka.digitaka.collector.CollectorAdapter
import com.imbuka.digitaka.collector.CollectorListFragment
import com.imbuka.digitaka.collector.VacationSpots.collectorList
import java.security.AccessController
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //initialise views
        toolbar = findViewById(R.id.activity_main_toolbar)

        //Get HostFragment and Nav Controller
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController

        //define AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        //connect toolbar with Nav Controller
        toolbar.setupWithNavController(navController, appBarConfiguration)


    }




}