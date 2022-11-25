package com.example.productdetails

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

import com.example.productdetails.databinding.DetailsLayoutBinding


import com.google.android.material.tabs.TabLayout

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var carouselImageAdapter: com.example.productdetails.CarouselImageAdapter
    lateinit var binding: DetailsLayoutBinding
    lateinit var item: Item
    private lateinit var tbLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private val frList = listOf(ShopFragment.newInstance(),
    com.example.productdetails.BlankFragment.newInstance(),
        com.example.productdetails.BlankFragment.newInstance())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        item = ItemList.bsList[intent.getIntExtra("position", 0)]







        binding.exitButton2.setOnClickListener {
            finish()
        }
//        binding.toCartButton.setOnClickListener {
//            startActivity(Intent(this.baseContext, CartActivity::class.java))
//        }

        tbLayout = binding.tabLayout


        carouselImageAdapter= com.example.productdetails.CarouselImageAdapter(
            this,
            (item.anotherImages + item.mainImage).reversed()
        )

        viewPager=binding.imageCarousel
        setStatusBar()
        initPager()
        init()
        tabLayoutInit()
        initFragmentFirst()







    }

    private fun tabLayoutInit() {
        tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                supportFragmentManager.beginTransaction().replace(R.id.fragContainer, frList[tab?.position!!]).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
    private fun initPager() {
        viewPager.adapter=carouselImageAdapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(20))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            page.scaleY = 1 - (0.25f * Math.abs(position))

        })
        viewPager.setPageTransformer(compositePageTransformer)
    }
    private fun init() {
        binding.titlePD.text = item.model.toString()
        binding.ratingBar.rating = item.rating.toFloat()
    }

    private fun initFragmentFirst() {

        val bundle = Bundle()
        bundle.putInt("tag", ItemList.bsList.indexOf(item))
        val shopFr = ShopFragment.newInstance()
        shopFr.arguments=bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragContainer, shopFr).commit()
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.white)
        }
        WindowCompat.getInsetsController(this.window, window.decorView).isAppearanceLightStatusBars = true
    }
}