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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

class MovieDetailPage : ComponentActivity() {
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
@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieDetailScreen(navController: NavController, filmId: String, windowSizeClass: WindowSizeClass) {
    Log.d("MovieDetailScreen", "MovieDetailScreen: $filmId")

    val filmDetailVM: MainViewModel = viewModel()
    val movie by filmDetailVM.movieDetail.collectAsState()

    if (movie.title == "") {
        filmDetailVM.getMovieDetail(filmId)
    }

    if (movie.title != "") {
        LazyColumn() {
            // Titre + Image de fond du film
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    )
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + movie.backdrop_path,
                            //on affiche l'image en plus gros que dans la liste
                            builder = {
                                crossfade(true)
                                size(600, 600)
                            }),
                        contentDescription = "Image film ${movie.title}",
                        Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .fillMaxWidth()
                    )
                }
            }
            // Affiche l'image promotionelle du film et ses genres
            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + movie.poster_path,
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }),
                        contentDescription = "Image film ${movie.title}",
                        Modifier.padding(start = 25.dp, end = 10.dp, top = 5.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                    ) {

                        Text(
                            text = movie.genres.toString(),
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                }
            }
            // Synopsis
            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "Synopsis",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = movie.overview,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }
            if(movie.credits.cast.isNotEmpty()){
                item {
                    Text(
                        text = "Têtes d'affiches",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
                items(movie.credits.cast.take(10)){ cast ->
                    FloatingActionButton(
                        onClick = { navController.navigate("DetailPerson/${cast.id}") },
                        modifier = Modifier.padding(20.dp),
                        containerColor = Color.White,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = "https://image.tmdb.org/t/p/w780" + cast.profile_path,
                                        builder = {
                                            crossfade(true)
                                            size(
                                                350,
                                                400
                                            )
                                        }),
                                    contentDescription = "Image film ${cast.name}",
                                    Modifier.padding(start = 5.dp, end = 5.dp)
                                )
                                Text(
                                    text = cast.name,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                                Text(
                                    text = cast.character,
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 15.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

