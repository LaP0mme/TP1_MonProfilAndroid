package com.devandroidisis.monprofil_tp1


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movie = MutableStateFlow<TmbMovieList>(TmbMovieList())
    val serie = MutableStateFlow<TmbSerieList>(TmbSerieList())
    val person = MutableStateFlow<TmbPersonList>(TmbPersonList())
    val movieDetail = MutableStateFlow<TmbMovieDetail>(TmbMovieDetail())
    val serieDetail = MutableStateFlow<TmbSerieDetail>(TmbSerieDetail())
    val personDetail = MutableStateFlow<TmbPersonDetail>(TmbPersonDetail())

    val api_key = "f89f9900127ccb4a33497f1decfc4358"

    val Service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmbApi::class.java)

    fun searchMovie(query: String) {
        viewModelScope.launch {
            Service.searchMovie(api_key, "fr", query).results
        }
    }

    fun searchSerie(query: String) {
        viewModelScope.launch {
            Service.searchSerie(api_key, "fr", query).results
        }
    }

    fun searchPerson(query: String) {
        viewModelScope.launch {
            Service.searchPerson(api_key, "fr", query).results
        }
    }

    fun getMovieList() {
        viewModelScope.launch {
            Service.movieList(api_key, "fr").results
        }
    }

    fun getSerieList() {
        viewModelScope.launch {
            Service.serieList(api_key, "fr").results
        }
    }

    fun getPersonList() {
        viewModelScope.launch {
            Service.personList(api_key, "fr").results
        }
    }

    fun getMovieDetail(movieID: String) {
        viewModelScope.launch {
            Service.movieDetail(movieID, api_key, "fr")
        }
    }

    fun getSerieDetail(serieID: String) {
        viewModelScope.launch {
            Service.serieDetail(serieID, api_key, "fr")
        }
    }

    fun getPersonDetail(personID: String) {
        viewModelScope.launch {
            Service.personDetail(personID, api_key, "fr")
        }
    }
}


