package com.example.mealtracker.ui.theme.functions

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


abstract class MealDatabase(context: Context) : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {
    companion object {
        private const val databaseName = "MealTracker.db"
        private const val databaseVersion = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Creating database table
        val createTableSQL = "create table meals (foodID INTEGER PRIMARY KEY AUTOINCREMENT, foodName TEXT, calories INTEGER)"
        db.execSQL(createTableSQL)
    }
}

data class Meal(val id: Int, val name: String, val calories: Int, val ingredients: List<String>)

sealed class MealResult<out T> {
    data class MealAdded(val meal: Meal) : MealResult<Meal>()
    data class MealError(val message: String) : MealResult<Nothing>()
}

data class MealAdded(val meal: Meal) : MealResult<Meal>()
data class MealUpdated(val meal: Meal) : MealResult<Meal>()
data class MealError(val message: String) : MealResult<Nothing>()

fun addMeal(name: String, calories: Int, ingredients: List<String>): MealResult<Meal> {
    return try {
        if (name.isBlank() || ingredients.isEmpty()) {
            return MealResult.MealError("Meal name or ingredients cannot be blank") // error message if function does not work
        }
        val newMeal = Meal(1, name, calories, ingredients) // meal with data of calories and ingredients
        MealResult.MealAdded(newMeal)
    } catch (e: Exception) { //exception that gets rejected
        MealResult.MealError("Error adding meal: ${e.message}")
    }
}

private fun Nothing?.isEmpty(): Boolean {
    return null == true
}

fun updateMeal(meal: Meal, newName: String, newCalories: Int): MealResult<Meal> {
    return try {
        val updatedMeal = meal.copy(name = newName, calories = newCalories)
        MealUpdated(updatedMeal)
    } catch (exception: Exception) {
        MealError("Error updating meal: ${exception.message}")
    }
}

fun searchMeals(query: String, meals: List<Meal>): List<Meal> { //search fucntion-implement into other pages if neccssary
    val lowercaseQuery = query.toLowerCase()
    return meals.filter { it.name.toLowerCase().contains(lowercaseQuery) }
}