package com.devandroidisis.monprofil_tp1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController) {
    var searchActive by remember { mutableStateOf(false) } // État pour indiquer si la recherche est active
    var searchText by remember { mutableStateOf("") } // État pour stocker le texte de recherche
    val imeAction = rememberUpdatedState(ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color(8, 65, 92),
                titleContentColor = Color.White
            ),
            title = {
                Text(text = if (searchActive) "" else "TurboApp") // Masquer le texte quand la recherche est active
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("HomeScreen") }) {
                    Icon(
                        Icons.Filled.Home, "Home",
                        tint = Color.White,
                    )
                }
            },
            actions = {
                if (searchActive) {
                    // Bouton de fermeture de la recherche
                    IconButton(
                        onClick = {
                            searchActive = false
                            searchText = "" // Effacer le texte de recherche
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                    }
                    IconButton(onClick = { searchActive = false }) {
                        Icon( // Icône de fermeture
                            Icons.Filled.Close,
                            contentDescription = "Fermer la recherche",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                    // Champ de texte de recherche quand la recherche est active
                    val containerColor = Color(56, 134, 151)
                    TextField(
                        value = searchText,
                        onValueChange = { newText ->
                            searchText = newText
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = imeAction.value
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // Action de validation (Done) gérée ici
                                keyboardController?.hide()
                                navController.navigate("SearchScreen/${searchText}")
                            }
                        ),
                        modifier = Modifier.padding(16.dp),
                        textStyle = TextStyle(color = Color.White),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                    )
                } else {
                    IconButton(
                        onClick = {
                            searchActive = true
                        },
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Icone de recherche",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    navController: NavController, windowClass: WindowSizeClass, filmBoolean: Boolean = false,
    seriesBoolean: Boolean = false, personBoolean: Boolean = false
) {
    val tintPerson = if (personBoolean) Color.White else Color(56, 134, 151)
    val tintFilm = if (filmBoolean) Color.White else Color(56, 134, 151)
    val tintSerie = if (seriesBoolean) Color.White else Color(56, 134, 151)

    BottomAppBar(
        containerColor = Color(8, 65, 92),
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { navController.navigate("MovieListScreen") },
                    modifier = Modifier.size(80.dp)
                ) {
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
                            color = tintFilm
                        )
                    }
                }

                IconButton(
                    onClick = { navController.navigate("SeriesListScreen") },
                    modifier = Modifier.size(80.dp)
                ) {
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

                IconButton(
                    onClick = { navController.navigate("ActeursListScreen") },
                    modifier = Modifier.size(80.dp)
                ) {
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
                            text = "Acteurs",
                            fontSize = 15.sp,
                            color = tintPerson
                        )
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeftNavBar(
    navController: NavController, windowClass: WindowSizeClass, filmBoolean: Boolean = false,
    seriesBoolean: Boolean = false, personBoolean: Boolean = false
) {

    val tintPerson = if (personBoolean) Color(153, 30, 41) else Color(154, 164, 165)
    val tintFilm = if (filmBoolean) Color(153, 30, 41) else Color(154, 164, 165)
    val tintSerie = if (seriesBoolean) Color(153, 30, 41) else Color(154, 164, 165)

    Surface(
        color = Color(235, 186, 185),
        contentColor = Color.White,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = { navController.navigate("MovieListScreen") },
                modifier = Modifier.size(80.dp)
            ) {
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
                        color = tintFilm
                    )
                }
            }

            IconButton(
                onClick = { navController.navigate("SeriesListScreen") },
                modifier = Modifier.size(80.dp)
            ) {
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

            IconButton(
                onClick = { navController.navigate("ActeursListScreen") },
                modifier = Modifier.size(80.dp)
            ) {
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
                        text = "Acteurs",
                        fontSize = 15.sp,
                        color = tintPerson
                    )
                }
            }
        }
    }
    var searchActive by remember { mutableStateOf(false) } // État pour indiquer si la recherche est active
    var searchText by remember { mutableStateOf("") } // État pour stocker le texte de recherche
    val imeAction = rememberUpdatedState(ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current

    if (searchActive) {
        // Bouton de fermeture de la recherche
        IconButton(
            onClick = {
                searchActive = false
                searchText = "" // Effacer le texte de recherche
            },
            modifier = Modifier.padding(end = 80.dp)
        ) {
        }
        IconButton(onClick = { searchActive = false }) {
            Icon( // Icône de fermeture
                Icons.Filled.Close,
                contentDescription = "Fermer la recherche",
                modifier = Modifier.size(24.dp),
                tint = Color(194, 1, 20)
            )
        }
        // Champ de texte de recherche quand la recherche est active
        val containerColor = Color(152, 58, 69)
        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction.value
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Action de validation (Done) gérée ici
                    keyboardController?.hide()
                    if (searchText != "") {
                        navController.navigate("SearchScreen/${searchText}")
                    }
                }
            ),
            modifier = Modifier.padding(start = 80.dp).fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { searchActive = true },
                modifier = Modifier.padding(20.dp),
                shape = CircleShape,
                containerColor = Color(235, 186, 185)
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Icone de recherche",
                    modifier = Modifier.size(24.dp),
                    tint = Color(153, 30, 41)
                )
            }
        }

    }
}

