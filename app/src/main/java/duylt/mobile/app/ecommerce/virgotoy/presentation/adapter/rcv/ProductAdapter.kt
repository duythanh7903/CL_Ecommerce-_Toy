package duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Product
import duylt.mobile.app.ecommerce.virgotoy.utils.Constants


class ProductAdapter: BaseQuickAdapter<Product, BaseViewHolder>(R.layout.item_product) {
    init {
        addChildClickViewIds(R.id.iconAddCart)
    }

    override fun convert(holder: BaseViewHolder, item: Product) {
        val productName =
            if (item.productName.length > 20) item.productName.substring(0, 20) + "..."
            else item.productName
        holder.setText(R.id.textProductName, productName)
            .setText(R.id.textPriceProduct, "${item.price} USD")
            .setText(R.id.textQuantity, "Quantity: ${item.quantity} ${if(item.quantity > 1) "products" else "product"}")
            .setText(R.id.textRating, "${if(item.sumVote == 0) 0 else (item.sumStar / item.sumVote)}")
            .setText(R.id.textSold, "Sold: ${item.purchases} ${if (item.purchases > 1) "products" else "product"}")
        Glide.with(context).load(
            "${Constants.URL}/${item.images[0].pathImage}"
        ).into(holder.getView(R.id.imageProduct))
    }
}