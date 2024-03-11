package duylt.mobile.app.ecommerce.virgotoy.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("created_by_manager")
    var createdByManager: String = "",
    @SerializedName("created_by_company")
    var createdByCompany: String = "",
    @SerializedName("id_category")
    var idCategory: String = "",
    @SerializedName("product_name")
    var productName: String = "",
    @SerializedName("gia_nhap")
    var cost: Int = 0,
    @SerializedName("gia_ban")
    var price: Int = 0,
    @SerializedName("quantity")
    var quantity: Int = 0,
    @SerializedName("purchases")
    var purchases: Int = 0,
    @SerializedName("sum_star")
    var sumStar: Int = 0,
    @SerializedName("sum_vote")
    var sumVote: Int = 0,
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("updated_at")
    var updatedAt: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("manager_name")
    var managerName: String = "",
    @SerializedName("company_name")
    var companyName: String = "",
    @SerializedName("category_name")
    var categoryName: String = "",
    @SerializedName("images")
    var images: MutableList<ImageProduct> = mutableListOf()
): java.io.Serializable {
}