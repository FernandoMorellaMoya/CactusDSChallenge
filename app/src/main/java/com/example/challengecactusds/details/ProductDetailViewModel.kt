package com.example.challengecactusds.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengecactusds.BASE_URL
import com.example.challengecactusds.data.Product
import com.example.challengecactusds.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductDetailViewModel():ViewModel() {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val api: ApiService = retrofit.create(ApiService::class.java)

    val productInfo = MutableLiveData<Product>()

    //Calls the API in order to fetch the selected Product information
    fun getProductInfo(id: Int) {
        var call = api.fetchProduct(id)

        call.enqueue(object : Callback<Product?> {
            override fun onResponse(
                call: Call<Product?>,
                response: Response<Product?>
            ) {
                response.body()?.let { product -> productInfo.postValue(product)}
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                call.cancel()
            }
        })
    }

}