package duylt.mobile.app.ecommerce.virgotoy.presentation.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.techiness.progressdialoglibrary.ProgressDialog
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.databinding.ActivityHomeBinding
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Product
import duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv.ProductAdapter
import duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel.HomeViewModel
import duylt.mobile.app.ecommerce.virgotoy.utils.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private lateinit var navController: NavController

    override fun initView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.layoutMainContainer) as NavHostFragment
        navController = navHostFragment.navController

        setupWithNavController(binding.bottomNavigationView, navController)
    }
}