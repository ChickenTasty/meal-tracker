package com.example.mealtracker

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mealtracker.ui.theme.MealTrackerTheme
import com.example.mealtracker.ui.theme.functions.NavGraph
import com.google.firebase.FirebaseApp




class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MealTrackerTheme {
                navController = rememberNavController()
                NavGraph(navController = navController) // Pass the navController here
            }
        }
    }
}
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}
