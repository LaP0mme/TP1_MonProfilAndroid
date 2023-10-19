package com.devandroidisis.monprofil_tp1

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmbApi {
    @GET("trending/movie/week")
    suspend fun movieList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbMovieList

    @GET("movie/{id}?append_to_response=credits")
    suspend fun movieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbMovieDetail

    @GET("trending/tv/week")
    suspend fun serieList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbSerieList

    @GET("tv/{id}?append_to_response=credits")
    suspend fun serieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbSerieDetail

    @GET("trending/person/week")
    suspend fun personList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbPersonList

    @GET("person/{id}")
    suspend fun personDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbPersonDetail

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbMovieList

    @GET("search/tv")
    suspend fun searchSerie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbSerieList

    @GET("search/person")
    suspend fun searchPerson(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TmbPersonList
}