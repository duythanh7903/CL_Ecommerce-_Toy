package duylt.mobile.app.ecommerce.virgotoy.domain.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by_manager")
    val createdByManager: String,
    val id: String,
    @SerializedName("manager_name")
    val managerName: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("index")
    val index: Int
)