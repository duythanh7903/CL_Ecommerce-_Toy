package duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.domain.model.ImageProduct
import duylt.mobile.app.ecommerce.virgotoy.utils.Constants

class ImageProductAdapter :
    BaseQuickAdapter<ImageProduct, BaseViewHolder>(R.layout.item_image_product) {
    override fun convert(holder: BaseViewHolder, item: ImageProduct) {
        Glide.with(context)
            .load("${Constants.URL}/${item.pathImage}")
            .into(holder.getView(R.id.imageProductItem))
    }
}