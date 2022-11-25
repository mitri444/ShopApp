package com.example.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cart.databinding.RecyclerShopBinding


class CartItemAdapter: RecyclerView.Adapter<CartItemAdapter.CartItemHolder>() {

    class CartItemHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = RecyclerShopBinding.bind(item)
        fun bind(cartItem: CartItem)= with(binding){
            imageCartItem.setImageResource(cartItem.image)
            titleCart.text = cartItem.title
            countText.text = cartItem.count.toString()
            priceCart.text = "$ ${cartItem.totalPrice}.00"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_shop, parent,false)
        return CartItemHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {

            val item = CartItemsList.listOfItem[position]

            holder.binding.imageMinus.setOnClickListener {
                if (item.count!=0) item.count--
                item.totalPrice = item.count*item.price

                notifyDataSetChanged()
            }
            holder.binding.imagePlus.setOnClickListener {
                item.count++
                item.totalPrice = item.count*item.price
                notifyDataSetChanged()
            }
        holder.bind(CartItemsList.listOfItem[position])





    }

    override fun getItemCount(): Int {
        return CartItemsList.listOfItem.size
    }


}