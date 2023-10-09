package com.devandroidisis.monprofil_tp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devandroidisis.monprofil_tp1.ui.theme.MonProfil_TP1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MonProfil_TP1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(windowSizeClass)
                }



            }
        }
    }
}



@Composable
fun Home( windowClass : WindowSizeClass) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "HomeScreen"){
        composable("HomeScreen"){
            HomeScreen(navController, windowClass)
        }
        composable("MovieListScreen"){
            MovieListScreen(navController, windowClass)
        }
        composable("SeriesListScreen"){
            SeriesListScreen(navController, windowClass)
        }
        composable("ActeursListScreen"){
            ActeursListScreen(navController, windowClass)
        }
    }
}




@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonProfil_TP1Theme {
        val windowSizeClass = calculateWindowSizeClass(ComponentActivity())
        Home(windowSizeClass)
    }
}
