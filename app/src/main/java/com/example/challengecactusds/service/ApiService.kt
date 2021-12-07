package com.example.challengecactusds.service

import com.example.challengecactusds.data.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun fetchAllProducts():Call<List<Product>>

    @GET("products/{id}")
    fun fetchProduct(@Path("id") id:Int):Call<Product>

    @GET("products")
    fun fetchQueryProducts(@Query("q") q:String):Call<List<Product>>
}