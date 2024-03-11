package duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel

import androidx.compose.runtime.key
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duylt.mobile.app.ecommerce.virgotoy.data.source.remote.RetrofitInstance
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Category
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(), Observable {
    private val productService = RetrofitInstance.productService
    private val categoriesService = RetrofitInstance.categoryService

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val products = MutableLiveData<MutableList<Product>>()
    val categories = MutableLiveData<MutableList<Category>>()

    @Bindable
    val inputKeySearch = MutableLiveData<String?>()

    init {
        getAllProductAndCategories()
    }

    private fun getAllProductAndCategories() {
        viewModelScope.launch {
            with(Dispatchers.IO) {
                try {
                    isLoading.postValue(true)
                    products.postValue(productService.getAllProducts())
                    categories.postValue(categoriesService.getAllCategories())
                    isLoading.postValue(false)
                } catch (e: Exception) {
                    isLoading.postValue(false)
                    errorMessage.postValue("Something went wrong!")
                }
            }
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}