package com.example.productdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CarouselImageAdapter(fragment: FragmentActivity, val listItems: List<Int>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = listItems.size
    override fun createFragment(position: Int): Fragment {
        val fragment = ImageFragment()
        fragment.arguments = Bundle().apply {
            putInt(IMG_OBJECT, listItems[position])
        }
        return fragment
    }
}