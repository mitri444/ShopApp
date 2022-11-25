package com.example.testapp

object NewItemsArray {
    var list = listOf<CarouselItem>(
        CarouselItem(true, "Iphone 12", "Súper. Mega. Rápido.", R.drawable.iphone, true),
        CarouselItem(false, "Samsung Galaxy A71", "Súper. Mega. Rápido.", R.drawable.samsung, true),
        CarouselItem(false, "Xiaomi Mi 11 ultra", "Súper. Mega. Rápido.", R.drawable.xiaomi, true)
    )
}
