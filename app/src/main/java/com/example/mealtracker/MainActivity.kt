package com.example.mealtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mealtracker.ui.theme.MealTrackerTheme
import com.example.mealtracker.ui.theme.functions.NavGraph


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            MealTrackerTheme {
                navController = rememberNavController()
                NavGraph(navController = navController) // Pass the navController here
            }
        }
    }
}
