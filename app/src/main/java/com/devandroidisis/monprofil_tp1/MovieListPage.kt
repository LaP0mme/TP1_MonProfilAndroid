package com.devandroidisis.monprofil_tp1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


class MovieListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel: MainViewModel by viewModels()

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: NavController, windowClass: WindowSizeClass) {
    val mainViewModel: MainViewModel = viewModel()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                         TopNavBar(navController)
                },
                bottomBar = {
                        BottomNavBar(navController, windowClass, filmBoolean = true,
                            seriesBoolean = false, personBoolean = false)
                },
            ) {
                val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
                MovieList(mainViewModel, navController, nbColumn = 2, modifier = modifier)
            }
        }
            else -> {
                Scaffold(
                    topBar = {
                        LeftNavBar(navController, windowClass, filmBoolean = true,
                            seriesBoolean = false, personBoolean = false)
                    }) {
                    val modifier = Modifier.padding(start= 80.dp)
                    MovieList(mainViewModel, navController, nbColumn = 3 ,modifier = modifier)
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList (movieViewModel : MainViewModel, navController: NavController, nbColumn: Int, modifier: Modifier){
    val movies by movieViewModel.movies.collectAsState()

    if(movies.results.isEmpty()){
        movieViewModel.getFilmInitiaux()
    }
    if (movies.results.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(nbColumn), modifier = modifier) {
            items(movies.results) { movie ->
                FloatingActionButton(
                    onClick = {
                        Log.d("MovieList", "MovieList: ${movie.id}")
                        navController.navigate("MovieDetailScreen/${movie.id}")},
                    modifier = Modifier.padding(20.dp),
                    containerColor = Color(89, 131, 129),
                ) {
                    Column( verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                        Column( verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(
                                    data = "https://image.tmdb.org/t/p/w780" + movie.poster_path,
                                    builder = {
                                        crossfade(true)
                                        size(
                                            350,
                                            400
                                        )
                                    }),
                                contentDescription = "Image film ${movie.title}",
                                Modifier.padding(start = 5.dp, end = 5.dp)
                            )
                        }
                        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)){
                            Text(text = movie.title,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 5.dp))
                            Text(text = movie.release_date,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 15.dp))
                        }
                    }
                }
            }
        }
    }
}

