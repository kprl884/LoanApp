package com.loanmanagementapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.loanmanagementapp.screen.Screen
import com.loanmanagementapp.utils.ResponsivenessCheckerPreview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") // Since we use Scaffold on every screen, we do not use padding value here
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navProvider: NavProvider,
) {
    Scaffold(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.LoginScreen
        ) {
            navProvider.screens.forEach { navProviderItem ->
                navProviderItem.screen.registerGraph(
                    navGraphBuilder = this,
                    navController = navController
                )
            }
        }
    }
}

@ResponsivenessCheckerPreview
@Composable
private fun NavGraphPreview() {
    Scaffold() {
        Column(
            modifier = Modifier
                .background(Color(0xFF267DFF))
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}