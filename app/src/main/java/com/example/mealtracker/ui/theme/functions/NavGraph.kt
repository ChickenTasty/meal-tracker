package com.example.mealtracker.ui.theme.functions

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavHost




@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = screen.HomePage.route
    ){
        composable( route = screen.HomePage.route){

        }
    }

}


