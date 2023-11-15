package com.example.mealtracker.ui.theme.MainScreens

import com.example.mealtracker.ui.theme.MealTrackerTheme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import android.app.AlertDialog
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.platform.LocalContext
import com.example.mealtracker.ui.theme.functions.screen
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*





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
                    onClick = {/*Navigate to ArchivePage*/
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
                ) {Button(onClick = { /*addmeal function here*/
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
                .height(700.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(9.dp))
                .align(Alignment.BottomCenter)
        ){
            Text(
                text = "Meals eaten today",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.White
            )
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





