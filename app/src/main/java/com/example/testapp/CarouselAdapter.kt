package com.example.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CarouselAdapter(fragment: FragmentActivity, val listItems: List<Any>) : FragmentStateAdapter(fragment) {
    private val sizeOf = listItems.size
    override fun getItemCount(): Int = sizeOf
    override fun createFragment(position: Int): Fragment {
        val fragment = ItemsFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position)
        }
        return fragment
    }

}