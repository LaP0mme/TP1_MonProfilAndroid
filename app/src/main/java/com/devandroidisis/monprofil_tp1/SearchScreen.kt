package com.devandroidisis.monprofil_tp1

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    fun SearchScreen(navController: NavController, search: String) {
        Scaffold(
            topBar = {
                TopNavBar(navController)
            }
        ) {
            SearchComposable(navController, search)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchComposable(navController: NavController, search: String) {

        // ViewModel
        val SearchViewModel: MainViewModel = viewModel()
        // Déclaration des variables pour les chips
        var searchedAll by remember { mutableStateOf(true) }
        var searchedMovie by remember { mutableStateOf(false) }
        var searchedSerie by remember { mutableStateOf(false) }
        var searchedActeur by remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 60.dp)
        ) {
            Text(
                text = "Résultats pour : $search",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .align(CenterHorizontally)
            )
            // FilterChip avec : Tout, Films, Série, Acteurs pour trier la recherche
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            ) {
                item {
                    FilterChip(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            searchedAll = !searchedAll
                            if (searchedAll) {
                                searchedMovie = false
                                searchedSerie = false
                                searchedActeur = false
                            }
                        },
                        label = {
                            Text("Tout")
                        },
                        selected = searchedAll,
                        leadingIcon = if (searchedAll) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
                item {
                    FilterChip(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            searchedMovie = !searchedMovie
                            if (searchedMovie) searchedAll = false
                        },
                        label = {
                            Text("Films")
                        },
                        selected = searchedMovie,
                        leadingIcon = if (searchedMovie) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
                item {
                    FilterChip(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            searchedSerie = !searchedSerie
                            if (searchedSerie) searchedAll = false
                        },
                        label = {
                            Text("Series")
                        },
                        selected = searchedSerie,
                        leadingIcon = if (searchedSerie) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
                item {
                    FilterChip(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            searchedActeur = !searchedActeur
                            if (searchedActeur) searchedAll = false
                        },
                        label = {
                            Text("Acteurs")
                        },
                        selected = searchedActeur,
                        leadingIcon = if (searchedActeur) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
            }
            val SearchViewModel: MainViewModel = viewModel()
            val movies by SearchViewModel.movies.collectAsState()
            val series by SearchViewModel.series.collectAsState()
            val Acteurs by SearchViewModel.persons.collectAsState()

            LazyColumn(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                content = {
                    item {
                        if (searchedMovie || searchedAll) {
                            SearchViewModel.getMovieSearch(search)
                            Text(
                                text = "Films",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            )
                            if (movies.results.isNotEmpty()) {
                                LazyRow() {
                                    items(movies.results.take(10)) { movie ->
                                        FloatingActionButton(
                                            onClick = { navController.navigate("MovieDetailScreen/${movie.id}") },
                                            modifier = Modifier.padding(20.dp),
                                            containerColor = Color.White
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = CenterHorizontally,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Image(
                                                    painter = rememberImagePainter(
                                                        data = "https://image.tmdb.org/t/p/w780" + movie.poster_path,
                                                        builder = {
                                                            crossfade(true)
                                                            size(
                                                                250,
                                                                200
                                                            )
                                                        }),
                                                    contentDescription = "Image film ${movie.title}"
                                                )
                                                Text(
                                                    text = movie.title,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item {
                        if (searchedSerie || searchedAll) {
                            SearchViewModel.getSerieSearch(search)
                            Text(
                                text = "Séries",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            )
                            if (series.results.isNotEmpty()) {
                                LazyRow() {
                                    items(series.results.take(10)) { serie ->
                                        FloatingActionButton(
                                            onClick = { navController.navigate("SerieDetailScreen/${serie.id}") },
                                            modifier = Modifier.padding(20.dp),
                                            containerColor = Color.White
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = CenterHorizontally,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Image(
                                                    painter = rememberImagePainter(
                                                        data = "https://image.tmdb.org/t/p/w780" + serie.poster_path,
                                                        builder = {
                                                            crossfade(true)
                                                            size(
                                                                250,
                                                                200
                                                            )
                                                        }),
                                                    contentDescription = "Image film ${serie.name}"
                                                )
                                                Text(
                                                    text = serie.name,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item {
                        if (searchedActeur || searchedAll) {
                            SearchViewModel.getPersonSearch(search)
                            Text(
                                text = "Acteurs",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp)
                            )
                            if (Acteurs.results.isNotEmpty()) {
                                LazyRow() {
                                    items(Acteurs.results.take(10)) { Acteur ->
                                        FloatingActionButton(
                                            onClick = { navController.navigate("ActeurDetailScreen/${Acteur.id}") },
                                            modifier = Modifier.padding(20.dp),
                                            containerColor = Color.White
                                        ) {
                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = CenterHorizontally,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Image(
                                                    painter = rememberImagePainter(
                                                        data = "https://image.tmdb.org/t/p/w780" + Acteur.profile_path,
                                                        builder = {
                                                            crossfade(true)
                                                            size(
                                                                250,
                                                                200
                                                            )
                                                        }),
                                                    contentDescription = "Image film ${Acteur.name}"
                                                )
                                                Text(
                                                    text = Acteur.name,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }

