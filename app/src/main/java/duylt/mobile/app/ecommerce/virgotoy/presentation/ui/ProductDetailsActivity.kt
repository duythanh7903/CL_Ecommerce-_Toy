package duylt.mobile.app.ecommerce.virgotoy.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.databinding.ActivityProductDetailsBinding
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Product
import duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv.ImageProductAdapter
import duylt.mobile.app.ecommerce.virgotoy.utils.Constants
import duylt.mobile.app.ecommerce.virgotoy.utils.base.BaseActivity
import duylt.mobile.app.ecommerce.virgotoy.utils.base.setClickAffect2

class ProductDetailsActivity : BaseActivity<ActivityProductDetailsBinding>(R.layout.activity_product_details) {

    private lateinit var imageAdapter: ImageProductAdapter
    private var productDetails: Product? = null

    override fun initView() {
        getProductDetails()
        imageAdapter = ImageProductAdapter().apply {
            setNewInstance( productDetails?.images ?: mutableListOf() )
            setOnItemClickListener { _, _, positioin ->
                val image = data[positioin]
                Glide.with(this@ProductDetailsActivity)
                    .load("${Constants.URL}/${image.pathImage}")
                    .into(binding.imageProduct)
            }
        }
        binding.apply {
            rcvImageProduct.adapter = imageAdapter

            iconBack.setClickAffect2 { finish() }
        }
    }

    private fun getProductDetails() {
        val bundle = intent.extras
        if (bundle != null) {
            productDetails = bundle.getSerializable("product_details") as Product
            if (productDetails != null) { showProductDetails(productDetails!!) }
        }
    }

    private fun showProductDetails(product: Product) {
        binding.apply {
            textProductName.apply {
                text = product.productName
                isSelected = true
            }
            Glide.with(this@ProductDetailsActivity)
                .load("${Constants.URL}/${product.images[0].pathImage}")
                .into(imageProduct)
            textPrice.text = "Price: ${product.price} USD"
            textQuantity.text = "Quantity: ${product.quantity} ${if(product.quantity > 1) "products" else "product"}"
            textRating.text = "${if(product.sumVote == 0) 0 else (product.sumStar / product.sumVote)}"
            textSold.text = "Sold: ${product.purchases} ${if (product.purchases > 1) "products" else "product"}"
            textDescription.text = product.description
            textCompanyName.text = "Made by ${product.companyName}"
            textCategory.text = "Category: ${product.categoryName}"
        }
    }
}