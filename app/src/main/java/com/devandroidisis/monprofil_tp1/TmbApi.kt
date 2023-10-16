package com.devandroidisis.monprofil_tp1

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmbApi {
    @GET("trending/movie/week")
    suspend fun movieList(@Query("api_key") apiKey: String,
                          @Query("language") language: String) : TmbMovieList

    @GET("movie/{id}?append_to_response=credits")
    suspend fun movieDetail(@Path("id") id: String,
                            @Query("api_key") apiKey: String,
                            @Query("language") language: String) : TmbMovieDetail

    @GET("trending/tv/week")
    suspend fun serieList(@Query("api_key") apiKey: String,
                          @Query("language") language: String) : TmbSerieList

    @GET("tv/{tv_id}?append_to_response=credits")
    suspend fun serieDetail(@Path("tv_id") serieID: String,
                            @Query("api_key") apiKey: String,
                            @Query("language") language: String) : TmbSerieDetail

    @GET("trending/person/week")
    suspend fun personList(@Query("api_key") apiKey: String,
                           @Query("language") language: String) : TmbPersonList

    @GET("person/{person_id}")
    suspend fun personDetail(@Path("person_id") personID: String,
                             @Query("api_key") apiKey: String,
                             @Query("language") language: String) : TmbPersonDetail

    @GET("search/movie")
    suspend fun searchMovie(@Query("api_key") apiKey: String,
                            @Query("language") language: String,
                            @Query("query") query: String) : TmbMovieList

    @GET("search/tv")
    suspend fun searchSerie(@Query("api_key") apiKey: String,
                            @Query("language") language: String,
                            @Query("query") query: String) : TmbSerieList

    @GET("search/person")
    suspend fun searchPerson(@Query("api_key") apiKey: String,
                             @Query("language") language: String,
                             @Query("query") query: String) : TmbPersonList
}