package com.devandroidisis.monprofil_tp1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhotoProfil(modifier: Modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.photomonprofil),
                contentDescription = "My profile picture",
                Modifier.clip(CircleShape).size(230.dp),
            )
        }

@Composable
fun Identite(modifier: Modifier = Modifier){
    Text(
        text = "Damien Lopez",
        fontWeight = FontWeight.Bold,
        modifier = modifier,
        fontSize = 40.sp
    )
}

@Composable
fun Promotion(modifier: Modifier = Modifier){
    Text(
        text = "Etudiant FIE4 - ISIS",
        modifier = modifier
    )
}

@Composable
fun Ecole(modifier: Modifier = Modifier){
    Text(
        text = "Ecole d'ingénieurs ISIS - INU Champollion'",
        fontStyle = FontStyle.Italic,
        modifier = modifier
    )
}

@Composable
fun Mail(modifier: Modifier = Modifier){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.email),
            contentDescription = "Mail icon",
            modifier = modifier.size(20.dp, 20.dp)
        )
        Text(
            text = "lopezdamien375@gmail.com",
            modifier = modifier
        )
    }
}

@Composable
fun LinkedIn(modifier: Modifier = Modifier){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.linkedin),
            contentDescription = "LinkedIn icon",
            modifier = modifier.size(20.dp, 20.dp)
        )
        Text(
            text = "www.linkedin.com/in/damien-lopez",
            modifier = modifier
        )
    }
}

@Composable
fun BouttonDemarrer(modifier: Modifier = Modifier){
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Démarrer")
    }

    }





