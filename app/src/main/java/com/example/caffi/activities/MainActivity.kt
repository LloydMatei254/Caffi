package com.example.caffi.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.caffi.adapters.CategoryAdapter
import com.example.caffi.adapters.PopularAdapter
import com.example.caffi.databinding.ActivityMainBinding
import com.example.caffi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initPopular()
        initBottomMenu()

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer { popularItems ->
            if (popularItems.isNotEmpty()) {
                binding.popularView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.popularView.adapter = PopularAdapter(popularItems)
                binding.progressBarPopular.visibility = View.GONE
            }
        })
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer { categories ->
            if (categories.isNotEmpty()) {
                binding.categoryView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.categoryView.adapter = CategoryAdapter(categories)
                binding.progressBarCategory.visibility = View.GONE
            }
        })
        viewModel.loadCategory()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { banners ->
            if (banners.isNotEmpty()) {
                Glide.with(this@MainActivity)
                    .load(banners[0].url)
                    .into(binding.banner)
                binding.progressBarBanner.visibility = View.GONE
            }
        })
        viewModel.loadBanner()
    }
}