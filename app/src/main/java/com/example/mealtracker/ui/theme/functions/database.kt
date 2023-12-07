package com.example.mealtracker.ui.theme.functions

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

// Initialising the firebase
val MealDatabase = FirebaseDatabase.getInstance()
val mealsRef = MealDatabase.getReference("meals")

// Adding meal function
fun addMeal(mealID: String, mealData: Map<String, Any>) {
    mealsRef.child(mealID).setValue(mealData)
}

// Function to add a meal
suspend fun addMeal(mealID: String, mealData: Meal) {
    try {
        mealsRef.child(mealID).setValue(mealData).await()
        println("Meal added successfully!")
    } catch (e: Exception) {
        println("Error adding meal: $e")
    }
}