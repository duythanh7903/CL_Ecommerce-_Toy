package duylt.mobile.app.ecommerce.virgotoy.presentation.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.techiness.progressdialoglibrary.ProgressDialog
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.databinding.FragmentHomeBinding
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Category
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Product
import duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv.CategoryAdapter
import duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv.ProductAdapter
import duylt.mobile.app.ecommerce.virgotoy.presentation.ui.ProductDetailsActivity
import duylt.mobile.app.ecommerce.virgotoy.presentation.viewmodel.HomeViewModel
import duylt.mobile.app.ecommerce.virgotoy.utils.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var products: MutableList<Product>
    private lateinit var categories: MutableList<Category>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var progressDialog: ProgressDialog

    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        products = mutableListOf()
        categories = mutableListOf()
        productAdapter = ProductAdapter().apply {
            setNewInstance(products)
            setOnItemChildClickListener { _, view, position ->
                when (view.id) {
                    R.id.iconAddCart -> {}
                }
            }
            setOnItemClickListener { _, _, position ->
                val product = data[position]
                showDetails(product)
            }
        }
        categoryAdapter = CategoryAdapter().apply {
            setNewInstance(categories)
            setOnItemClickListener { _, _, position ->
                val category = data[position]
                this.indexSelected = category.index

                handleFilterProductById(category)
            }
        }
        progressDialog = ProgressDialog(requireActivity()).apply { isCancelable = false }
        binding.apply {
            lifecycleOwner = this@HomeFragment
            rcvCategory.apply {
                adapter = categoryAdapter
            }
            rcvProduct.apply {
                adapter = productAdapter
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        viewModel.apply {
            isLoading.observe(this@HomeFragment) {
                if (it) progressDialog.show() else progressDialog.dismiss()
            }

            errorMessage.observe(this@HomeFragment) {
                if (!it.isNullOrEmpty()) Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG)
                    .show()
            }

            products.observe(this@HomeFragment) {
                this@HomeFragment.products.apply {
                    clear()
                    addAll(it)
                }
                productAdapter.notifyDataSetChanged()
            }

            categories.observe(this@HomeFragment) {
                this@HomeFragment.categories.apply {
                    clear()
                    addAll(it)
                }
                categoryAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun handleFilterProductById(category: Category) {
        val idCategoryAllDefault = "all"
        productAdapter.setNewInstance(
            if (category.id != idCategoryAllDefault) {
                products.filter { it.idCategory == category.id }
                        as MutableList<Product>
            } else {
                products
            }
        )
    }

    private fun showDetails(product: Product) {
        val intent = Intent(requireActivity(), ProductDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("product_details", product)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}