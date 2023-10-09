package com.devandroidisis.monprofil_tp1


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<TmbMovieList>(TmbMovieList())
    val series = MutableStateFlow<TmbSerieList>(TmbSerieList())
    val persons = MutableStateFlow<TmbPersonList>(TmbPersonList())
    val movieDetail = MutableStateFlow<TmbMovieDetail>(TmbMovieDetail())
    val serieDetail = MutableStateFlow<TmbSerieDetail>(TmbSerieDetail())
    val personDetail = MutableStateFlow<TmbPersonDetail>(TmbPersonDetail())

    val api_key = "f89f9900127ccb4a33497f1decfc4358"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val api = service.create(com.devandroidisis.monprofil_tp1.TmbApi::class.java)

    fun getFilmInitiaux(){
        viewModelScope.launch {
            movies.value = api.movieList(api_key, "fr")
        }
    }
    fun getSeriesInitiaux(){
        viewModelScope.launch {
            series.value = api.serieList(api_key, "fr")
        }
    }

    fun getPersonsInitiaux(){
        viewModelScope.launch {
            persons.value = api.personList(api_key,"fr")
        }
    }

    fun getMovieDetail(movieid: String){
        viewModelScope.launch {
            movieDetail.value = api.movieDetail(movieid, api_key, "fr")
        }
    }

    fun getSerieDetail(serieid: String){
        viewModelScope.launch {
            serieDetail.value = api.serieDetail(serieid, api_key, "fr")
        }
    }

    fun getPersonDetail(personid: String){
        viewModelScope.launch {
            personDetail.value = api.personDetail(personid, api_key, "fr")
        }
    }

    fun getMovieSearch(query: String){
        viewModelScope.launch {
            movies.value = api.searchMovie(query, api_key, "fr")
        }
    }

    fun getSerieSearch(query: String){
        viewModelScope.launch {
            series.value = api.searchSerie(query, api_key, "fr")
        }
    }

    fun getPersonSearch(query: String){
        viewModelScope.launch {
            persons.value = api.searchPerson(query, api_key, "fr")
        }
    }

}