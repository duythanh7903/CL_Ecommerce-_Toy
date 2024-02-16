package duylt.mobile.app.ecommerce.virgotoy.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.databinding.ActivitySplashBinding
import duylt.mobile.app.ecommerce.virgotoy.utils.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this@SplashActivity, HomeActivity::class.java)
            )
        }, 3000)
    }

}