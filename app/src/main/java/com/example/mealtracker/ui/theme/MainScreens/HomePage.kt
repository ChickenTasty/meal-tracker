package com.example.mealtracker.ui.theme.MainScreens

import com.example.mealtracker.ui.theme.MealTrackerTheme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import android.app.AlertDialog
import androidx.compose.ui.platform.LocalContext
import com.example.mealtracker.ui.theme.functions.Screen



class SainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealTrackerTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }


    data class Meal(val id: Int, val name: String, val calories: Int)

    sealed class MealResult<out T> {
        class MealAdded(newMeal: Meal) : MealResult<Meal>() {

        }
    }

    data class MealAdded(val meal: Meal) : MealResult<Meal>()
    data class MealUpdated(val meal: Meal) : MealResult<Meal>()
    data class MealError(val message: String) : MealResult<Nothing>()

    fun addMeal(name : String, calories: Int, ingredients: List<String>): MealResult<Meal> {
        return try {
            val newMeal = Meal(1, name, calories)
            MealResult.MealAdded(newMeal)
        } catch (e: Exception) {
            MealError("Error adding meal: ${e.message}")
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

    @Composable
    fun mainPage(navController: NavController) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Gray
        )  {}
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //make each text a button to move pages
                    Button(
                        onClick = {/*go to progress page*/
                                  navController.navigate(route = Screen.ProgressPage.route)
                                  },
                        modifier = Modifier.padding(1.dp)
                    ) {
                        Text(
                            text = "Progress",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(1.dp),
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {/*Already at home page do not do anything*/  },
                        modifier = Modifier.padding(1.dp)
                    ) {
                        Text(
                            text = "Home",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(1.dp),
                            color = Color.Red
                        )
                    }
                    Button(
                        onClick = {/*Navigate to ArchivePage*/  },
                        modifier = Modifier.padding(1.dp)
                    ) {
                        Text(
                            text = "Archive",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(1.dp),
                            color = Color.White
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color.White
                )
                Box(
                    modifier = Modifier
                        .border(
                            1.dp,
                            Color.Black,
                            shape = MaterialTheme.shapes.extraLarge
                        )
                        .padding(5.dp)
                ) {
                    Box(
                        modifier = Modifier.width(80.dp),
                        contentAlignment = Alignment.Center
                    ) {Button(onClick = { openAddMeal() },
                        modifier = Modifier.width(80.dp)
                    ) {}
                        Text(
                            text = "Add Meal",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 5.dp),
                            color = Color.White
                        )
                    }



                }
            }



            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Black,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(640.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(9.dp))
            ){
                Text(
                    text = "Meals eaten today",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = Color.White
                )

            }
            openAddMeal()

        }
    }
}
@Composable
fun openAddMeal() {//pop up box to let user add meal
    val context = LocalContext.current

    AlertDialog.Builder(context)
        .setTitle("Add Meal")
        .setPositiveButton("Add") { dialog, which -> /*add add meal stuff here */}

        .setNegativeButton("cancel") { dialog, which -> /* add closing the tab here*/ }
}
fun navigate(navController: String) {
    TODO("Not yet implemented")
}


fun navigate(navController: NavController, route: String) {
    navController.navigate(route)
}


@Preview(showBackground = true)
@Composable
fun mainPrev(){
    MealTrackerTheme {
        val navController = rememberNavController()
        HomePage(navController = navController)
    }
}





