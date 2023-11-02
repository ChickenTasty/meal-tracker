package com.example.mealtracker.ui.theme.MainScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mealtracker.ui.theme.functions.screen

@Composable
fun progressPage(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Gray
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //make each text a button to move pages
                Button(
                    onClick = { /* Nothing happens, already at progress page */ },
                    modifier = Modifier.padding(1.dp)
                ) {
                    Text(
                        text = "Progress",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(1.dp),
                        color = Color.Red
                    )
                }
                Button(
                    onClick = { /* Handle Progress button click here */
                              navController.navigate(route = screen.HomePage.route)
                              },
                    modifier = Modifier.padding(1.dp)
                ) {
                    Text(
                        text = "Home",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(1.dp),
                        color = Color.White
                    )
                }
                Button(
                    onClick = { /* Handle Progress button click here */
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
                    .height(30.dp)
                    .width(300.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(9.dp))
            ) // Add some sort of streak(weekly)

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(9.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(9.dp))
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun progressPagePrev() {
    progressPage(navController = rememberNavController())
}