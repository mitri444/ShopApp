package com.example.productdetails

import android.graphics.Color
import android.media.Image
import android.media.Rating

data class Item(val brand: String,
                val mainImage: Int,
                val oldPrice: Int,
                val newPrice: Int,
                var isFavourite:Boolean = false,
                val id:Int,
                val cpu:String = "Exynos 990",
                val camera: String = "108 + 12 mp",
                val capacity: List<String> = listOf("128", "256"),
                val colors: List<String> = listOf("#772D03", "#010035"),
                val rating: Double = 4.5,
                val sd: Int = 256,
                val ssd: Int = 8,
                val anotherImages:List<Int> = listOf(R.drawable.samsung_second),
                val model: String)

