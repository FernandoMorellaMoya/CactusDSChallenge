package com.example.challengecactusds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengecactusds.data.Product
import com.example.challengecactusds.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://challenge.cactusds.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get All Products from the API
        getProducts()

        //SearchView listener for when the user makes a query search
        findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                findViewById<SearchView>(R.id.searchView).clearFocus()
                if (query != null) {
                    getQueryProducts(query)
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getQueryProducts(newText)
                }
                return false
            }
        })

    }

    //Get all the products from the API function
    private fun getProducts() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val api = retrofitBuilder.create(ApiService::class.java)

        api.fetchAllProducts().enqueue(object : Callback<List<Product>?> {
            override fun onResponse(
                call: Call<List<Product>?>,
                response: Response<List<Product>?>
            ) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {

            }
        })

    }

    //Display the products on the RecyclerView (calls ProductsAdapter)
    fun showData(products:List<Product>){
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ProductsAdapter(products, this@MainActivity)

        }
    }

    //Search products by Query function
    private fun getQueryProducts(data:String){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val api = retrofitBuilder.create(ApiService::class.java)

        api.fetchQueryProducts(data).enqueue(object : Callback<List<Product>?> {
            override fun onResponse(
                call: Call<List<Product>?>,
                response: Response<List<Product>?>
            ) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {

            }
        })
    }
}