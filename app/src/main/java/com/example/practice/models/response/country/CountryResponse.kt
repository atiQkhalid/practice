package com.example.practice.models.response.country

data class CountryResponse(
    val errors: List<String>,
    val results: Int,
    val response: List<String>
)