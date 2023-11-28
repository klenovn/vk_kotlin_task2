package com.example.vk_kotlin_task2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_task2.models.CatModel
import com.example.vk_kotlin_task2.providers.RetrofitController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatListViewModel : ViewModel() {
    private val PER_PAGE: Int = 10
    private var page: Int = 0

    private val _cats = MutableLiveData<List<CatModel>>()
    val cats: LiveData<List<CatModel>> get() = _cats

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    fun getCats(tags: String = "", limit: Int = PER_PAGE, skip: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(true)
                val response = RetrofitController.apiService.getCats(tags, limit, skip)
                _cats.postValue(response)
                _error.postValue(false)
            } catch (e: Exception) {
                _error.postValue(true)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun loadMoreCats() {
        page += 1
        getCats(skip = page * PER_PAGE)
    }
}