package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "User",
                        tint = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp)
                            .scale(1.2f)
                    )
                }
                Text(
                    text = "Journalia",
                    color = Color.Black,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 5.dp),
                    fontFamily = FontFamily(Font(R.font.nature))
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Sidebar",
                        tint = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 10.dp)
                            .scale(1.2f)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 15.dp))
            var textFieldValue by remember { mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                    ,
                    value = textFieldValue,
                    onValueChange = {
                        textFieldValue = it
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    placeholder = {
                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Search for posts, deadlines and info",
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.padding(start = 10.dp))
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search icon"
                            )
                        }
                    }
                )
            }
        }
    }
}
