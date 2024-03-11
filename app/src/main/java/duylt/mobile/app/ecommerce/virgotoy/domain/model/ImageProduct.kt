package duylt.mobile.app.ecommerce.virgotoy.domain.model

import com.google.gson.annotations.SerializedName

data class ImageProduct(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("id_product")
    var idProduct: String = "",
    @SerializedName("path_image")
    var pathImage: String = ""
): java.io.Serializable {
}