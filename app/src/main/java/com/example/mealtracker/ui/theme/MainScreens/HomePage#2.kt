package com.example.mealtracker.ui.theme.MainScreens

package com.example.killmealready

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


@Preview
@Composable
fun mainPage2(){
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
                    onClick = { /* Handle Progress button click here */ },
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
                    onClick = { /* Handle Progress button click here */ },
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
                    onClick = { /* Handle Progress button click here */ },
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
                ) {Button(onClick = { /*Add meal clicked*/ },
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
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(Color.DarkGray)
                ) {
                    Text(
                        text = "Time Stamps",
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .background(Color.DarkGray),
                ) {
                    Text(
                        text = "Graphs",
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun mainprev2(){
    mainPage2()
}
