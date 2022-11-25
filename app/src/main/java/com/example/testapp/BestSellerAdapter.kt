package com.example.testapp

import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetails.Item
import com.example.productdetails.ItemList
import com.example.productdetails.ProductDetailsActivity
import com.example.testapp.databinding.RecyclerBsBinding
import java.util.*

class BestSellerAdapter: RecyclerView.Adapter<BestSellerAdapter.BestSellerHolder>() {



    private val listOfRaw = mutableListOf<Int>()
    class BestSellerHolder(item:View): RecyclerView.ViewHolder(item) {
        init {
            item.setOnClickListener{
                val intent = Intent(it.context, ProductDetailsActivity::class.java)
                intent.putExtra("position", adapterPosition)
                it.context.startActivity(intent)
            }
        }
        val binding = RecyclerBsBinding.bind(item)
        fun bind(bsItem: Item) = with(binding) {
            image.setImageResource(bsItem.mainImage)
            if (bsItem.isFavourite) favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            itemTitle.text = "${bsItem.brand} ${bsItem.model}"
            newPrice.text = "$"+String.format(Locale.US, "%,d",bsItem.newPrice)
            oldPrice.text = "$"+String.format(Locale.US, "%,d",bsItem.oldPrice)
            oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_bs, parent,false)
        return BestSellerHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerHolder, position: Int) {

        if (ItemList.bsList[holder.adapterPosition].isFavourite) listOfRaw+=holder.adapterPosition
        val item = ItemList.bsList[position]
        holder.bind(item)
        val imageButton = holder.binding.favButton
        imageButton.setOnClickListener {
            if (listOfRaw.contains(holder.adapterPosition)) {
                Log.d("tag", "1")
                listOfRaw.remove(holder.adapterPosition)
                item.isFavourite = false
            }
            else {
                Log.d("tag", "2")
                listOfRaw.add(holder.adapterPosition)
                item.isFavourite = true
            }
            notifyDataSetChanged()
        }
        if (listOfRaw.contains(position)) {
            imageButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else imageButton.setImageResource(R.drawable.favourite_border)

    }

    override fun getItemCount(): Int {
        return ItemList.bsList.size
    }



}