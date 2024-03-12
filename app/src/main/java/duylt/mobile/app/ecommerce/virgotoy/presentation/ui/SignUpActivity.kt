package duylt.mobile.app.ecommerce.virgotoy.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.techiness.progressdialoglibrary.ProgressDialog
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.databinding.ActivitySignUpBinding
import duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel.SignUpViewModel
import duylt.mobile.app.ecommerce.virgotoy.utils.base.BaseActivity
import duylt.mobile.app.ecommerce.virgotoy.utils.base.showToast

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun initView() {
        binding.apply {
            lifecycleOwner = this@SignUpActivity
            viewModel = this@SignUpActivity.viewModel

            progressDialog = ProgressDialog(this@SignUpActivity)

            observer()
        }
    }

    private fun observer() {
        viewModel.apply {
            isCloseScreen.observe(this@SignUpActivity) {
                if (it) finish()
            }

            errorMessage.observe(this@SignUpActivity) {
                if (!it.isNullOrEmpty()) {
                    showToast(it)
                    errorMessage.postValue("")
                }
            }

            isSuccess.observe(this@SignUpActivity) {
                if (it) {
                    finish()
                    showToast("Register account successful!")
                }
            }

            isLoading.observe(this@SignUpActivity) {
                if (it) progressDialog.show() else progressDialog.dismiss()
            }
        }
    }
}