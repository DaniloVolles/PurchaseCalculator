package com.example.purchasecalculator.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.purchasecalculator.model.Product
import com.example.purchasecalculator.repository.ProductRepository

class InclusionFormViewModel (application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository.getInstance(application.applicationContext)

    private var _saveProduct = MutableLiveData<Boolean>()
    val saveProduct: LiveData<Boolean> = _saveProduct

    private var _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun insert(product: Product){
        _saveProduct.value = repository.insert(product)
    }

    fun load(id: Int){
        _product.value = repository.get(id)
    }
}
