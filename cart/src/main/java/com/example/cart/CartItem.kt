package com.example.cart

data class CartItem(val id: Int, val image: Int,val title: String, val price: Int, var count: Int, var totalPrice:Int = count*price)
