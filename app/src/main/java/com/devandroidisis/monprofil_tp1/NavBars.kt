package com.devandroidisis.monprofil_tp1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController, windowClass: WindowSizeClass) {
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Blue,
                titleContentColor = Color.White
            ),
            title = {
                Text("TurboApp")
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("HomeScreen") }) {
                    Icon(Icons.Filled.Home, "Home")
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Search, "Search")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    navController: NavController, windowClass: WindowSizeClass, filmBoolean: Boolean = false,
    seriesBoolean: Boolean = false, personBoolean: Boolean = false)
{
    val tintPerson = if (personBoolean) Color.White else Color.Black
    val tintFilm = if (filmBoolean) Color.White else Color.Black
    val tintSerie = if (seriesBoolean) Color.White else Color.Black

    BottomAppBar(
        containerColor = Color.Blue,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { navController.navigate("MovieListScreen") },
                    modifier = Modifier.size(80.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.movie_24px),
                            contentDescription = "Movies_Button",
                            tint = tintFilm,
                            modifier = Modifier.size(35.dp)
                        )
                        Text(
                            text = "Films",
                            fontSize = 15.sp,
                            color = tintFilm)
                    }
                }

                IconButton(onClick = { navController.navigate("SeriesListScreen") },
                modifier = Modifier.size(80.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.tv_24px),
                            contentDescription = "Tv_Button",
                            tint = tintSerie,
                            modifier = Modifier.size(35.dp)
                        )
                        Text(
                            text = "Series",
                            fontSize = 15.sp,
                            color = tintSerie
                        )
                    }

                }

                IconButton(onClick = { navController.navigate("ActeursListScreen") },
                    modifier = Modifier.size(80.dp)) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_24px),
                            contentDescription = "Person_Button",
                            tint = tintPerson,
                            modifier = Modifier.size(35.dp)
                        )
                        Text(
                            text= "Acteurs",
                            fontSize = 15.sp,
                            color = tintPerson)
                    }
                }
            }
        },
    )
}
