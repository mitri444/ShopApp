package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.RecyclerCategoryBinding

class CategoryAdapter:RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val categoryList = listOf<CategoryItems>(
        CategoryItems(R.drawable.phone, R.string.phones),
        CategoryItems(R.drawable.computer, R.string.computer),
        CategoryItems(R.drawable.heart, R.string.health),
        CategoryItems(R.drawable.books, R.string.books)
    )
    private var row_index = 0

    class CategoryHolder(item:View): RecyclerView.ViewHolder(item) {
        val binding = RecyclerCategoryBinding.bind(item)
        fun bind(categoryItems: CategoryItems) = with(binding) {
            image.setImageResource(categoryItems.image)
            title.setText(categoryItems.string)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_category, parent,false)
        return CategoryAdapter.CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
        val image = holder.binding.image
        val text = holder.binding.title
        holder.binding.image.setOnClickListener {
            row_index = holder.adapterPosition
            notifyDataSetChanged()
        }
        if (row_index==holder.adapterPosition) {
            image.setBackgroundResource(R.drawable.orange_button)
            image.setColorFilter(ContextCompat.getColor(holder.binding.root.context, R.color.white))
            text.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.orange))
        }
        else {
            image.setBackgroundResource(R.drawable.round_button)
            image.setColorFilter(ContextCompat.getColor(holder.binding.root.context, R.color.unactive_gray))
            text.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.black))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}