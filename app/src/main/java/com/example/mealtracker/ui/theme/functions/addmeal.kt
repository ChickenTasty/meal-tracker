@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)

package com.example.mealtracker.ui.theme.functions

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun openAddMeal(navController: NavController) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var carbonfootprint by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = mealName,
            onValueChange = { mealName = it },
            label = { Text("Meal Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        TextField(
            value = calories,
            onValueChange = { calories = it },
            label = { Text("Calories") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        TextField(
            value = carbonfootprint,
            onValueChange = { carbonfootprint = it },
            label = { Text("Carbon Foot Print") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        TextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Ingredients") },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Cancel", fontSize = 13.sp)
            }

            Button(
                onClick = {
                    // Close the keyboard
                    keyboardController?.hide()

                    val ingredientsList = ingredients.split(",").map { it.trim() }
                    val caloriesInt = calories.toIntOrNull() ?: 0
                    addMeal(mealName, caloriesInt, carbonfootprint, ingredientsList)

                    // Assuming mealName, ingredients, calories, and carbonfootprint are mutable variables
                    mealName = ""
                    ingredients = ""
                    calories = ""
                    carbonfootprint = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Add Meal")
            }
        }
    }
}

fun addMeal(mealName: String, calories: Int, carbonfootprint: String, ingredients: List<String>) {
    try {
        // Initializing firebase
        val mealDatabase = FirebaseDatabase.getInstance()
        val mealsRef = mealDatabase.getReference("meals")

        // Generating unique ID for meals
        val mealID = UUID.randomUUID().toString()


        val mealData = mapOf(
            "mealName" to mealName,
            "calories" to calories,
            "ingredients" to ingredients,
            "carbon foot print" to carbonfootprint
        )

        // Adding meal to the database
        mealsRef.child(mealID).setValue(mealData)
    } catch (e: Exception) {
        Log.e("AddMeal", "Error adding meal to Firebase", e)
    }
}

data class Meal(
    val mealName: String,
    val calories: Int,
    val carbonfootprint: Int,
    val ingredients: String
)