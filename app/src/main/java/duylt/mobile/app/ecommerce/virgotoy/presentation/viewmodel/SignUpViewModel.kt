package duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duylt.mobile.app.ecommerce.virgotoy.data.source.local.datastorage.SharePrefUtils
import duylt.mobile.app.ecommerce.virgotoy.data.source.remote.RetrofitInstance
import duylt.mobile.app.ecommerce.virgotoy.domain.model.MessageResponse
import duylt.mobile.app.ecommerce.virgotoy.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModel(), Observable {
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) =
        Unit

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) =
        Unit

    private val accountService = RetrofitInstance.accountService
    @Bindable val email = MutableLiveData("")
    @Bindable val phoneNumber = MutableLiveData("")
    @Bindable val password = MutableLiveData("")
    val errorMessage = MutableLiveData("")
    val isLoading = MutableLiveData(false)
    val isSuccess = MutableLiveData(false)
    val isCloseScreen = MutableLiveData(false)

    fun closeScreen() {
        isCloseScreen.postValue(true)
    }

    fun handleSignUp() {
        val emailVal = email.value ?: ""
        val phoneNumberVal = phoneNumber.value ?: ""
        val passwordVal = password.value ?: ""
        val registerModel =
            MessageResponse.RegisterModel(
                email = emailVal,
                phoneNumber = phoneNumberVal,
                password = passwordVal
            )

        val isNullInput =
            emailVal.isNullOrEmpty()
                    || phoneNumberVal.isNullOrEmpty()
                    || passwordVal.isNullOrEmpty()

        isSuccess.postValue(false)

        if (isNullInput) {
            errorMessage.postValue("Fields cannot be left blank!")
            isLoading.postValue(false)
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    errorMessage.postValue("")
                    isLoading.postValue(true)
                    try {
                        val messageResponse = accountService.registerAccount(registerModel)
                        if (messageResponse.code == Constants.SUCCESS_CODE_RESPONSE) {
                            errorMessage.postValue("")
                            isSuccess.postValue(true)
                        } else {
                            errorMessage.postValue(messageResponse.message)
                        }
                    } catch (e: Exception) {
                        errorMessage.postValue("An error has occurred")
                        Log.e("bee", e.message.toString())
                    }
                    isLoading.postValue(false)
                }
            }
        }
    }
}