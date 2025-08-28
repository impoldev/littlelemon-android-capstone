package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Little Lemon logo",
                        modifier = Modifier
                            .width(260.dp)
                            .height(80.dp)
                            .padding(horizontal = 20.dp)
                    )
                    IconButton(
                        onClick = {navController.navigate(Profile.route)},
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.TopEnd)
                            .padding(horizontal = 20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile image",
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(percent = 100)
                                )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF485E57))
                        .padding(vertical = 40.dp),
                    Arrangement.Center
                ) {
                    Text(
                        text = "homeeeeeeeeeeeeee",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                var firstName: String by remember { mutableStateOf("") }
                var lastName: String by remember { mutableStateOf("") }
                var email: String by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Personal information",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 40.dp)

                    )

                    Column {
                        Text(text = "First name", modifier = Modifier.padding(bottom = 5.dp))
                        TextField(
                            value = firstName,
                            placeholder = { Text(text = "Mike") },
                            onValueChange = { firstName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                        )
                    }

                    Column {
                        Text(text = "Last Name", modifier = Modifier.padding(bottom = 5.dp))
                        TextField(
                            value = lastName,
                            placeholder = { Text(text = "Jones") },
                            onValueChange = { lastName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                        )
                    }

                    Column {
                        Text(text = "Email", modifier = Modifier.padding(bottom = 5.dp))
                        TextField(
                            value = email,
                            placeholder = { Text(text = "mike@example.com") },
                            onValueChange = { email = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF4CE14)
                ),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(text = "Register", color = Color(0xFF485E57))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}