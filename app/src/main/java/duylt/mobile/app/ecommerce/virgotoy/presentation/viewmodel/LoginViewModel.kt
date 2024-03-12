package duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duylt.mobile.app.ecommerce.virgotoy.data.source.local.datastorage.SharePrefUtils
import duylt.mobile.app.ecommerce.virgotoy.data.source.remote.RetrofitInstance
import duylt.mobile.app.ecommerce.virgotoy.domain.model.MessageResponse
import duylt.mobile.app.ecommerce.virgotoy.presentation.ui.SignUpActivity
import duylt.mobile.app.ecommerce.virgotoy.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel(), Observable {

    @Bindable val emailInput = MutableLiveData("")
    @Bindable val passInput = MutableLiveData("")
    val errorMessage = MutableLiveData("")
    val isLoading = MutableLiveData(false)
    val isSuccess = MutableLiveData(false)
    val isCloseScreen = MutableLiveData(false)
    private val accountService = RetrofitInstance.accountService

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) =
        Unit

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) =
        Unit

    fun register(context: Context) {
        val intent = Intent(context, SignUpActivity::class.java)
        context.startActivity(intent)
    }

    fun handleLogin() {
        val email = emailInput.value ?: ""
        val pass = passInput.value ?: ""
        val isNullInput = email.isNullOrEmpty() || pass.isNullOrEmpty()

        if (isNullInput) {
            errorMessage.postValue("Fields cannot be left blank!")
            isLoading.postValue(false)
            return
        }

        viewModelScope.launch {
            with(Dispatchers.IO) {
                isLoading.postValue(true)
                try {
                    val message = accountService.loginAccount(MessageResponse.LoginModel(email, pass))
                    if (message.code != Constants.SUCCESS_CODE_RESPONSE) {
                        errorMessage.postValue(message.message)
                    } else {
                        handleCacheAccount(message.account)
                        errorMessage.postValue("")
                        isSuccess.postValue(true)
                    }
                } catch (e: java.lang.Exception) {
                    errorMessage.postValue("An error has occurred")
                    Log.e("bee", e.message.toString())
                }
                isLoading.postValue(false)
            }
        }
    }

    private fun handleCacheAccount(account: MessageResponse.Account) {
        val jsonAccount = account.toJson()
        SharePrefUtils.jsonAccountCurrent = jsonAccount
        Log.d("bee", "Check Json Account: $jsonAccount")
    }
}