package com.example.mealtracker.ui.theme

import androidx.navigation.NavHost
import androidx.navigation.NavHostController


annotation class composable

@composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = screen.HomePage.route
    ){
        composable(route = screen.)
    }
}