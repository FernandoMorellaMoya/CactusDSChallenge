package com.example.challengecactusds.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import androidx.lifecycle.ViewModelProvider
import com.example.challengecactusds.R

class ProductDetails : AppCompatActivity() {

    lateinit var viewModel: ProductDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        getProductDetails()
    }

    //Displays the information obtained by getProductInfo(id) on the details UI
    private fun getProductDetails(){
        val id = intent.extras?.get("id") as Int

        viewModel.getProductInfo(id)

        viewModel.productInfo.observe(this, Observer { product ->
            findViewById<TextView>(R.id.pNameTextView).text = product.name
            findViewById<TextView>(R.id.descText).text = product.description
            findViewById<TextView>(R.id.priceText).text = "${getText(R.string.price_title)}  ${product.price}${getText(
                R.string.currency
            )}"
            //turns the photo url into and image
            Glide.with(this).load(product.photo).into(findViewById(R.id.imageView))
        })
    }
}