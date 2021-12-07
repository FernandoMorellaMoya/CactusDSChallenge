package com.example.challengecactusds

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengecactusds.data.Product
import com.example.challengecactusds.details.ProductDetails

class ProductsAdapter(private val products: List<Product>, private val context:Context) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.product.text = "${product.id + 1} - ${product.name}"

        //Click listener (when user clicks on any product displayed by the RecycleView it starts the details activity)
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, ProductDetails::class.java).putExtra("id",product.id))
        }
    }

    override fun getItemCount() = products.size

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val product:TextView = itemView.findViewById(R.id.id)
    }

}