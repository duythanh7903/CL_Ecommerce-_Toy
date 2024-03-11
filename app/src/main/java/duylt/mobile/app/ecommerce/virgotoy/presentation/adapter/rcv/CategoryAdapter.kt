package duylt.mobile.app.ecommerce.virgotoy.presentation.adapter.rcv

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import duylt.mobile.app.ecommerce.virgotoy.R
import duylt.mobile.app.ecommerce.virgotoy.domain.model.Category

class CategoryAdapter : BaseQuickAdapter<Category, BaseViewHolder>(R.layout.item_category) {

    var indexSelected: Int = 0
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @SuppressLint("ResourceAsColor")
    override fun convert(holder: BaseViewHolder, item: Category) {
        holder.setText(R.id.textCategory, item.categoryName)
            .setBackgroundResource(
                R.id.textCategory,
                if (item.index == indexSelected) R.drawable.bg_tab_selected
                else R.drawable.bg_tab_unselected
            )
            .setTextColor(
                R.id.textCategory,
                if (item.index == indexSelected) context.resources.getColor(R.color.text_white)
                else context.resources.getColor(R.color.primary)
            )
    }
}