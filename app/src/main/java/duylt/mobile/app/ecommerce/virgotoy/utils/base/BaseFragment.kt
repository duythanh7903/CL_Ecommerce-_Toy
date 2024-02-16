package duylt.mobile.app.ecommerce.virgotoy.utils.base

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes private val resId: Int) : Fragment() {
    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        return binding.root
    }

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }
    fun showActivity(act: Class<*>,requestCode: Int, bundle: Bundle?) {
        val intent = Intent(activity, act)
        intent.putExtras(bundle ?: Bundle())
        activity?.startActivityForResult(intent,requestCode)
    }

    fun showActivity(act: Class<*>, bundle: Bundle?) {
        val intent = Intent(activity, act)
        intent.putExtras(bundle ?: Bundle())
        startActivity(intent)
    }

    fun hasUsageStatsPermission(): Boolean {
        val appOps = requireActivity().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            requireContext().packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }


    fun hasOverlayPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(requireContext())
        } else {
            true
        }
    }


    abstract fun initData()
}