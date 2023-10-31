package com.example.mealtracker.ui.theme.functions

import androidx.navigation.NavHost
import androidx.navigation.NavHostController


annotation class composable

sealed class Screen(val route: String){
    object HomePage : Screen(route = "Home_Page")
    object HomePage2 : Screen(route = "Home_Page#2")
    object ProgressPage : Screen(route = "Progress_Page")
    object ProgressPage2 : Screen(route = "Progress_Page#2")
    object ArchivePage : Screen(route = "Archive_Page")

}

@composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.HomePage.route
    ){
        composable(route  = Screen.HomePage.route)
    }
}

object destinations {
    const val HomePage = "Home_Page"
    const val HomePage2 = "Home_Page#2"
    const val ProgressPage = "Progress_Page"
    const val ProgressPage2 = "Progress_Page#2"
    const val ArchivePage = "Archive_Page"
}

