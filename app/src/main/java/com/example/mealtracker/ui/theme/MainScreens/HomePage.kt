package com.example.mealtracker.ui.theme.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mealtracker.ui.theme.MealTrackerTheme
import com.example.mealtracker.ui.theme.functions.Meal
import com.example.mealtracker.ui.theme.functions.screen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


data class Meal(
    val mealName: String,
    val calories: String,
    val carbonfootprint: Int,
    val ingredients: String
)

@Composable
fun mainPage(navController: NavController) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var carbonfootprint by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance()
    val mealsRef = database.getReference("meals")
    var mealList by remember { mutableStateOf<List<Meal>>(emptyList()) }

    // Fetch data from Firebase using Coroutines
    LaunchedEffect(key1 = true) {
        GlobalScope.launch(Dispatchers.IO) {
            mealsRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val updatedmealList = mutableListOf<Meal>()
                    for (childSnapshot in snapshot.children) {
                        val meal = childSnapshot.getValue(Meal::class.java)
                        meal?.let { updatedmealList.add(it) }
                    }

                    mealList = updatedmealList
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Gray
    ) {}
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //make each text a button to move pages
                Button(
                    onClick = {
                        /*go to progress page*/
                        navController.navigate(route = screen.ProgressPage.route)
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
                    onClick = {
                              navController.navigate(route = screen.HomePage2.route)
                    },
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
                    onClick = {
                        /*Navigate to ArchivePage*/
                        navController.navigate(route = screen.ArchivePage.route)
                    },
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
                ) {
                    Button(
                        onClick = {
                            /*addmeal function here*/
                            navController.navigate(route = screen.addMealPage.route)
                        },
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
        Spacer(modifier = Modifier.height(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(9.dp))
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Meals Today",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 5.dp),
                color = Color.White
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                // Display the meal information in LazyColumn
                items(mealList) {meal ->
                    Column {
                        Text(
                            text = "Meal Name: ${meal.mealName}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Calories: ${meal.calories}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Ingredients: ${meal.ingredients}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Carbon foot print: ${meal.carbonfootprint}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun mainPrev(){
    MealTrackerTheme {
        val navController = rememberNavController()
        mainPage(navController = navController)
    }
}