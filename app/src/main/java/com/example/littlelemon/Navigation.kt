package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(database: MenuDao) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val startDestination = remember {
        val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val first = sharedPrefs.getString("first", "")
        val last = sharedPrefs.getString("last", "")
        val email = sharedPrefs.getString("email", "")
        if (first != "" && last != "" && email != "") Home.route else Onboarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Home.route) {
            Home(navController = navController, database)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
    }
}