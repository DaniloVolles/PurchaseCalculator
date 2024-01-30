package com.example.purchasecalculator.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.purchasecalculator.model.Product
import com.example.purchasecalculator.repository.ProductRepository

class ProductViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProductRepository.getInstance(application.applicationContext)

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    fun getAll(): List<Product> {
        _productList.value = repository.getAll()
        return _productList.value!!
    }

    fun delete(id: Int){
        repository.delete(id)
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}