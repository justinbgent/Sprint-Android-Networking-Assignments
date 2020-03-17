package com.example.myapplication.retrofit

import com.example.myapplication.model.Employee
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiInterface{
    @GET("employees")
    fun getEmployees(): Call<List<Employee>>

    @GET("employees/{id}")
    fun getEmployeeById(@Path("id")employeeId: String): Call<List<Employee>>

    @GET("employees")
    fun getEmployeeByAge(@Query("age")emloyeeAge: String): Call<List<Employee>>

    @POST("employees")
    fun addEmployee(@Body employee: Employee): Call<Employee>

    @PUT("employees")
    fun updateEmployee(@Body employee: Employee): Call<Employee>

    @DELETE("employees/{id}")
    fun deleteEmployeeById(@Path ("id") id: String): Call<Void>
}

class Factory {
    companion object{
        private const val BASE_URL = "http://demo8143297.mockable.io/"

        fun create(): ApiInterface{
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            logger.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}