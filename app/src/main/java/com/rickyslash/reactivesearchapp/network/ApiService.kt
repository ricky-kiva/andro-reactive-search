package com.rickyslash.reactivesearchapp.network

import com.rickyslash.reactivesearchapp.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("geocoding/{query}.json")
    suspend fun getCountry(
        @Path("query") query: String,
        @Query("key") accessToken: String,
        @Query("autocomplete") autoComplete: Boolean = true
    ): PlaceResponse

}