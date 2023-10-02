package com.devandroidisis.monprofil_tp1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devandroidisis.monprofil_tp1.R

@Composable
fun PhotoProfil(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.photomonprofil),
        contentDescription = "My profile picture",
        Modifier
            .clip(CircleShape)
            .size(230.dp),
    )
}

@Composable
fun Identite(modifier: Modifier = Modifier) {
    Text(
        text = "Damien Lopez",
        fontWeight = FontWeight.Bold,
        modifier = modifier,
        fontSize = 40.sp
    )
}

@Composable
fun Promotion(modifier: Modifier = Modifier) {
    Text(
        text = "Etudiant FIE4 - ISIS",
        modifier = modifier
    )
}

@Composable
fun Ecole(modifier: Modifier = Modifier) {
    Text(
        text = "Ecole d'ingénieurs ISIS - INU Champollion'",
        fontStyle = FontStyle.Italic,
        modifier = modifier
    )
}

@Composable
fun Mail(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.email),
            contentDescription = "Mail icon",
            modifier = modifier.size(20.dp, 20.dp)
        )
        Text(
            text = " lopezdamien375@gmail.com",
            modifier = modifier
        )
    }
}

@Composable
fun LinkedIn(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.linkedin),
            contentDescription = "LinkedIn icon",
            modifier = modifier.size(20.dp, 20.dp)
        )
        Text(
            text = " www.linkedin.com/in/damien-lopez",
            modifier = modifier
        )
    }
}

@Composable
fun BouttonDemarrer(modifier: Modifier = Modifier, navController: NavController) {
    Button(onClick = { navController.navigate("MovieListScreen") }, modifier = modifier) {
        Text(text = "Démarrer")
    }

}

@Composable
fun HomeScreen(navController: NavController, windowClass: WindowSizeClass) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    PhotoProfil()
                    Identite()
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Promotion()
                    Ecole()
                }
                Column {
                    Mail()
                    LinkedIn()
                }
                Column {
                    BouttonDemarrer(navController = navController)
                }
            }
        }

        else -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        PhotoProfil()
                        Identite()
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Promotion()
                        Ecole()
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column {
                        Mail()
                        LinkedIn()
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Column {
                        BouttonDemarrer(navController = navController)
                    }

                }
            }
        }
    }
}






