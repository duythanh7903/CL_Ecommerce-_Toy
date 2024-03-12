package duylt.mobile.app.ecommerce.virgotoy.presentation.ui

import android.content.Intent
import androidx.activity.viewModels
import com.techiness.progressdialoglibrary.ProgressDialog
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.databinding.ActivityLoginBinding
import duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel.LoginViewModel
import duylt.mobile.app.ecommerce.virgotoy.utils.base.BaseActivity
import duylt.mobile.app.ecommerce.virgotoy.utils.base.setClickAffect2
import duylt.mobile.app.ecommerce.virgotoy.utils.base.showToast

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun initView() {
        binding.apply {
            lifecycleOwner = this@LoginActivity
            viewModel = this@LoginActivity.viewModel

            progressDialog = ProgressDialog(this@LoginActivity)
        }

        onClick()
        observer()
    }

    private fun onClick() {
        binding.apply {
            iconBack.setClickAffect2 { finish() }
        }
    }

    private fun observer() {
        viewModel.apply {
            isLoading.observe(this@LoginActivity) {
                if (it) progressDialog.show() else progressDialog.dismiss()
            }

            errorMessage.observe(this@LoginActivity) {
                if (!it.isNullOrEmpty()) {
                    showToast(it)
                    errorMessage.postValue("")
                }
            }

            isSuccess.observe(this@LoginActivity) {
                if (it) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }
            }

            isCloseScreen.observe(this@LoginActivity) {
                if (it) finish()
            }
        }
    }
}