package com.loanmanagementapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.loanmanagementapp.navigation.NavGraph
import com.loanmanagementapp.navigation.NavProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navProvider: NavProvider
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavGraph(
                navController = navController,
                navProvider = navProvider
            )
        }
    }
}