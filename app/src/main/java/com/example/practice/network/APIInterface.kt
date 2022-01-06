package com.example.practice.network

import com.example.practice.models.response.CountryDetailResponse
import com.example.practice.models.response.country.CountryResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * The APIInterface.kt
 */
interface ApiInterface {

    @GET("countries")
    fun getAllCountries(): Call<CountryResponse>

    @GET("statistics")
    fun getCountryDetail(): Call<CountryDetailResponse>
}