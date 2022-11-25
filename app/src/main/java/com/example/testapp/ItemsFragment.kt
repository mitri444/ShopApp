package com.example.testapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testapp.databinding.FragmentItemsBinding

const val ARG_OBJECT = "object"

class ItemsFragment : Fragment() {
    lateinit var binding: FragmentItemsBinding
    private var listOfItems:List<CarouselItem> = NewItemsArray.list
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemsBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
                var item = listOfItems[getInt(ARG_OBJECT)]
                imageView2.setImageResource(item.picture)
                if (!item.isNew) newText.visibility = View.INVISIBLE
                if (!item.isBuy) buyButton.visibility = View.INVISIBLE
                textTitle.text = item.title
                textSubtitle.text = item.subtitle

            }

        }

    }

}