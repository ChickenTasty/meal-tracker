package com.example.mealtracker.ui.theme

import androidx.navigation.NavHost
import androidx.navigation.NavHostController


annotation class composable

sealed class Screen(val route: String){
    object HomePage : Screen("Home_Page")
    object HomePage2 : Screen("Home_Page#2")
    object ProgressPage : Screen ("Progress_Page")
    object ProgressPage2 : Screen ("Progress_Page#2")
    object ArchivePage : Screen ("Archive_Page")

}

@composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.HomePage.route
    ){
        composable(route = Screen.)
    }
}