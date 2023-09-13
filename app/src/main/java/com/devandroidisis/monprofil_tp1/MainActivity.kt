package com.devandroidisis.monprofil_tp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.devandroidisis.monprofil_tp1.ui.theme.MonProfil_TP1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonProfil_TP1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center , modifier = modifier.fillMaxSize()) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.photomonprofil),
                contentDescription = "My profile picture",
                Modifier.clip(CircleShape)
            )
            Text(
                text = "Damien Lopez",
                fontWeight = FontWeight.Bold,
                modifier = modifier
            )
            Text(
                text = "Etudiant FIE4 - ISIS",
                modifier = modifier
            )
            Text(
                text = "Ecole d'ingénieurs ISIS - INU Champollion'",
                fontStyle = FontStyle.Italic,
                modifier = modifier
            )

        }
        
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonProfil_TP1Theme {
    Greeting()
    }
}