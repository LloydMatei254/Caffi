package com.example.caffi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caffi.Repository.MainRepository
import com.example.caffi.domain.BannerModel
import com.example.caffi.domain.CategoryModel
import com.example.caffi.domain.ItemsModel

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    private val _banners = MutableLiveData<List<BannerModel>>()
    val banners: LiveData<List<BannerModel>> = _banners

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val _popular = MutableLiveData<List<ItemsModel>>()
    val popular: LiveData<List<ItemsModel>> = _popular

    fun loadBanner() {
        repository.loadBanner().observeForever {
            _banners.value = it
        }
    }

    fun loadCategory() {
        repository.loadCategory().observeForever {
            _categories.value = it
        }
    }

    fun loadPopular() {
        repository.loadPopular().observeForever {
            _popular.value = it
        }
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>> {
        return repository.loadItemCategory(categoryId)
    }
}

