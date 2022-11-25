package com.example.testapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.productdetails.ProductDetailsActivity
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.databinding.BadgeTextBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var bindingText: BadgeTextBinding
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var bsAdapter: BestSellerAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var bottomNavigationMenu: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavigationMenu = binding.bottomNavigationView
        bindingText = BadgeTextBinding.inflate(layoutInflater)

        updateCartCount()



        bsAdapter = BestSellerAdapter()
        categoryAdapter = CategoryAdapter()

        carouselAdapter = CarouselAdapter(this, NewItemsArray.list)





        setStatusBar()
        hideSystemBars()
        init()
        filterInit()
        bnvMenuSelected()
        adaptersInit()

    }

    private fun init() = with(binding) {
        rcCategory.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        rcCategory.adapter=categoryAdapter
        bsRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
        bsRecyclerView.adapter = bsAdapter
        viewPager2 = binding.newItemsCarousel
        viewPager2.adapter = carouselAdapter

    }
    private fun adaptersInit() {


        fun brandAdapter() {
            val adapterBrand = ArrayAdapter.createFromResource(
                this,
                R.array.brands,
                android.R.layout.simple_spinner_item
            )
            adapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.brandSpinner.adapter = adapterBrand
            binding.brandSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
        }


        fun sizeAdapter() {
            val adapterSizes = ArrayAdapter.createFromResource(
                this,
                R.array.sizes,
                android.R.layout.simple_spinner_item
            )
            adapterSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sizeSpinner.adapter = adapterSizes
            binding.sizeSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
        }

        fun priceAdapter() {
            val adapterPrices = ArrayAdapter.createFromResource(
                this,
                R.array.prices,
                android.R.layout.simple_spinner_item
            )
            adapterPrices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerPrice.adapter = adapterPrices
            binding.spinnerPrice.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
        }

        brandAdapter()
        priceAdapter()
        sizeAdapter()


    }
    private fun filterInit()= with(binding) {
        filterButton.setOnClickListener {
                rcView.visibility = View.GONE
                filterView.visibility = View.VISIBLE
        }
        exitButton.setOnClickListener {
            rcView.visibility = View.VISIBLE
            filterView.visibility = View.GONE
        }
    }
    private fun bnvMenuSelected() {
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.cart -> startActivity(Intent(this@MainActivity, com.example.cart.CartActivity::class.java))

                R.id.explore -> {}
                R.id.person -> {}
                R.id.favourites -> {}
            }
            true

        }

    }


    override fun onRestart() {
        super.onRestart()
        updateCartCount()
    }
    private fun updateCartCount() {

        val badge = bottomNavigationMenu.getOrCreateBadge(R.id.cart)
        badge.isVisible = true

        val items = com.example.cart.CartItemsList.totalCount()


        if(items!=0) badge.number =items
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.background_color)
        }
        WindowCompat.getInsetsController(this.window, window.decorView).isAppearanceLightStatusBars = true
    }
    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    }



}
