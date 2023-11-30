package com.example.mealtracker.ui.theme.functions

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mealtracker.ui.theme.MainScreens.archivePage
import com.example.mealtracker.ui.theme.MainScreens.mainPage
import com.example.mealtracker.ui.theme.MainScreens.mainPage2
import com.example.mealtracker.ui.theme.MainScreens.progressPage
import com.example.mealtracker.ui.theme.MainScreens.progressPage2

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = screen.HomePage.route
    ){
        composable( route = screen.HomePage.route){
            mainPage(navController = navController)
        }
        composable( route = screen.HomePage2.route){
            mainPage2(navController = navController)
        }
        composable( route = screen.ProgressPage.route){
            progressPage(navController = navController)
        }
        composable( route = screen.ProgressPage2.route){
            progressPage2(navController = navController)
        }
        composable( route = screen.ArchivePage.route){
            archivePage(navController = navController)
        }
        composable ( route = screen.addMealPage.route){
            openAddMeal(navController = navController)
        }
    }
}


