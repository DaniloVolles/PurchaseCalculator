package com.example.purchasecalculator.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.purchasecalculator.model.Product
import com.example.purchasecalculator.repository.ProductRepository

class InclusionFormViewModel (application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository.getInstance(application.applicationContext)

    private var _insertProduct = MutableLiveData<Boolean>()
    val saveProduct: LiveData<Boolean> = _insertProduct

    private var _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun insert(id: Int, store: String?, name: String, price: Double, amount: Double, type: String?){
        val product = Product(id, store, name, price, amount, type)
        if (id == 0) {
            _insertProduct.value = repository.insert(product)
        } else {
            TODO()
//            _saveProduct.value = repository.update(product)
        }
    }

    fun load(id: Int){
        _product.value = repository.get(id)
    }
}
