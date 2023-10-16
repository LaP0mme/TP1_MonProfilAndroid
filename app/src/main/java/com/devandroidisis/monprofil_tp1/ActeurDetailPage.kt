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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import java.util.regex.Pattern

class ActeurDetailPage : ComponentActivity() {
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
fun ActeurDetailScreen(navController: NavController, acteurId: String) {
    val acteurDetailVM: MainViewModel = viewModel()
    val acteur by acteurDetailVM.personDetail.collectAsState()

    if (acteur.name == "") {
        acteurDetailVM.getPersonDetail(acteurId)
    }

    if (acteur.name != "") {
//Column Globale
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            // Nom de la personne
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = acteur.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .align(CenterHorizontally)
                )
            }

            Image(
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/h632" + acteur.profile_path,
                    builder = {
                        crossfade(true)
                        size(500, 500)
                    }),
                contentDescription = "Photo de l'acteur ${acteur.name}",
                Modifier
                    .padding(start = 15.dp, end = 15.dp)
            )

            // Affiche + Date de sortie + Genre
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
            }

            var checkBirthday = false
            if (acteur.birthday != null) {
                checkBirthday = Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$\n", acteur.birthday)
            }
            if (acteur.place_of_birth != "" && checkBirthday) {
                Text(
                    text = "Lieu de naissance : " + acteur.place_of_birth,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                )

            }

            // Synopsis
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                if (acteur.biography != "") {
                    Text(
                        text = "Biographie",
                        color = Color.Black,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = acteur.biography,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
                else{
                    Text(
                        text = "Pas de biographie disponible",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }

        }

    }
}


