package com.example.productdetails

object ItemList {
    var bsList = listOf<Item>(
        Item("Samsung", R.drawable.samsung1, 1500, 1047, true, 111, model = "Galaxy s20 Ultra"),
        Item("Xiaomi",R.drawable.xiaomi1, 400, 300, true, 222,model = "Mi 10 Pro"),
        Item("Samsung", R.drawable.samsung2, 1500, 1047, true, 3333,model = "Note 20 Ultra"),
        Item("Motorola", R.drawable.edge1, 400, 300, true, 4444,model = "One Edge")
    )
}