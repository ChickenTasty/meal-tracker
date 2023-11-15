package com.example.mealtracker.ui.theme.functions

import android.app.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mealtracker.ui.theme.MealTrackerTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun openAddMeal(navController: NavController) {//pop up box to let user add meal
    var mealName by remember {mutableStateOf("") }
    var ingredients by remember {mutableStateOf("") }
    var calories by remember {mutableStateOf("") }
    var carbonfootprint by remember {mutableStateOf("")}


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
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "",
                        fontSize = 18.sp
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(route = screen.HomePage.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                ) {
                    Text(
                        "cancel",
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = { /* Handle add meal button click here */
                        val ingredientsList = ingredients.split(",").map { it.trim() } //converts string into a list of strings
                        val caloriesInt = calories.toIntOrNull() ?: 0 //converts string of numbers to int
                        val result = addMeal(mealName, caloriesInt, ingredientsList)

                        when (result) {
                            is MealResult.MealAdded -> {
                                println("Meal added successfully: ${result.meal}")
                                mealName = ""
                                ingredients = ""
                                calories = ""
                                carbonfootprint = ""
                            }
                            is MealResult.MealError -> {
                                println("Failed to add meal: ${result.message}")

                            }

                            else -> {
                            }
                        }},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Add Meal")
                }
            }
        }
    }
}
@Preview
@Composable
fun addMealPrev() {
    MealTrackerTheme {
        val navController = rememberNavController()
        openAddMeal(navController = navController)
    }
}
