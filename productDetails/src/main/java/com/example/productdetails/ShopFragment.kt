package com.example.productdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.ImageButton
import com.example.productdetails.databinding.FragmentShopBinding

import java.util.*


class ShopFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentShopBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentShopBinding.inflate(inflater)

        // Inflate the layout for this fragment
        var myInt = 0
        arguments?.getInt("tag")?.let {
            myInt = it
        }
        val item = ItemList.bsList[myInt]
        binding.apply {
            textViewCPU.text = item.cpu
            textViewcamera.text = item.camera
            textViewSD.text = item.sd.toString()+" GB"
            textViewSSD.text = item.ssd.toString()+" GB"
            binding.priceInButton.text = "$"+String.format(Locale.US, "%,d",item.newPrice)+".00"
        }



        binding.colorButtonFirst.setOnClickListener(this)
        binding.colorButtonSecond.setOnClickListener(this)
        binding.capacityFirst.setOnClickListener(this)
        binding.capacitySecond.setOnClickListener(this)



        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = ShopFragment()
    }

    override fun onClick(p0: View?) {
        val btn1 = binding.colorButtonFirst
        val btn2 = binding.colorButtonSecond

        when(translateIdToIndex(p0!!.id)) {
            2 -> replace(btn1, btn2)
            1 -> replace(btn2, btn1)
            4 -> replaceText(binding.capacityFirst,binding.capacitySecond)
            3 -> replaceText(binding.capacitySecond, binding.capacityFirst)

        }
    }

    private fun translateIdToIndex(id: Int): Int {
        var index = -1

        when (id) {
            R.id.colorButtonFirst -> index = 1
            R.id.colorButtonSecond -> index = 2
            R.id.capacityFirst -> index =3
            R.id.capacitySecond -> index =4
        }

        return index
    }

    private fun replace(buttonChecked: ImageButton, buttonNonChecked: ImageButton) {

        buttonChecked.setImageResource(R.drawable.void_pict)
        buttonNonChecked.setImageResource(R.drawable.check)
    }
    private fun replaceText(textChecked: CheckedTextView, textNonChecked: CheckedTextView) {
        Log.d("tag", "${R.id.colorButtonFirst},!!!!! ")
        textChecked.setBackgroundResource(R.drawable.void_pict)
        textChecked.setTextColor(resources.getColor(R.color.unactive_gray))
        textNonChecked.setBackgroundResource(R.drawable.orange_round)
        textNonChecked.setTextColor(resources.getColor(R.color.white))
    }


}