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

fun main() {
    // Example: Add a breakfast burrito meal
    val breakfastBurrito = mapOf(
        "mealName" to "Breakfast Burrito",
        "mealIngredients" to listOf("Eggs", "Tortilla", "Cheese", "Sausage", "Tomatoes", "Onions"),
        "calories" to 450
    )

    addMeal("mealID1", breakfastBurrito)
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