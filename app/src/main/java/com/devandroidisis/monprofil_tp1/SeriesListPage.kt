package com.devandroidisis.monprofil_tp1

import android.annotation.SuppressLint
import android.os.Bundle
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



class SeriesListActivity : ComponentActivity() {
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
fun SeriesListScreen(navController: NavController, windowClass: WindowSizeClass) {
    val mainViewModel: MainViewModel = viewModel()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    TopNavBar(navController)
                },
                bottomBar = {
                    BottomNavBar(navController, windowClass, filmBoolean = false,
                        seriesBoolean = true, personBoolean = false)
                },
            ) {
                val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
                SeriesList(mainViewModel, navController, nbColumn = 2 ,modifier = modifier)
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    LeftNavBar(navController, windowClass, filmBoolean = false,
                        seriesBoolean = true, personBoolean = false)
                }) {
                val modifier = Modifier.padding(start= 80.dp)
                SeriesList(mainViewModel, navController, nbColumn = 3 ,modifier = modifier)
            }


                }

        }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesList (seriesViewModel : MainViewModel, navController: NavController, nbColumn: Int, modifier: Modifier){
    val series by seriesViewModel.series.collectAsState()

    if(series.results.isEmpty()){
        seriesViewModel.getSeriesInitiaux()
    }
    if (series.results.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(nbColumn), modifier = modifier) {
            items(series.results) { serie ->
                FloatingActionButton(
                    onClick = {navController.navigate("SerieDetailScreen/${serie.id}")},
                    modifier = Modifier.padding(20.dp),
                    containerColor = Color.White,
                ) {
                    Column( verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                        Column( verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(
                                    data = "https://image.tmdb.org/t/p/w780" + serie.poster_path,
                                    builder = {
                                        crossfade(true)
                                        size(
                                            350,
                                            400
                                        )
                                    }),
                                contentDescription = "Image serie ${serie.name}",
                                Modifier.padding(start = 5.dp, end = 5.dp)
                            )
                        }
                        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)){
                            Text(text = serie.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 5.dp))
                            Text(text = serie.first_air_date,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 15.dp))
                        }
                    }
                }
            }
        }
    }
}

