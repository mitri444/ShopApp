package com.example.cart

object CartItemsList {
    var listOfItem = listOf(
        com.example.cart.CartItem(1, R.drawable.samsung_second, "Galaxy Note 20 Ultra", 1500, 1,),
        com.example.cart.CartItem(2, R.drawable.iphone_second, "iPhone 13", 1800, 1)
    )
    fun totalCount():Int {
        var sum = 0
        listOfItem.forEach{
            sum+=it.count
        }
        return sum
    }

}