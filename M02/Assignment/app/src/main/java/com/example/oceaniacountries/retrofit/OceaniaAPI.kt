package com.example.oceaniacountries.retrofit

import com.example.oceaniacountries.model.OceaniaCountry
import retrofit2.Call
import retrofit2.http.GET

interface OceaniaAPI{
    @GET("oceania")
    fun countryName(): Call<OceaniaCountry>
}