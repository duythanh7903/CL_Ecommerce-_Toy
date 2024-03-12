package duylt.mobile.app.ecommerce.virgotoy.domain.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("code")
    var code: Int = 0,
    @SerializedName("account")
    var account: Account
) {

    data class Account(
        @SerializedName("id")
        var id: String = "",
        @SerializedName("email")
        var email: String = "",
        @SerializedName("phone_number")
        var phoneNumber: String = "",
        @SerializedName("password")
        var password: String = "",
        @SerializedName("updated_at")
        var updatedAt: String = "",
        @SerializedName("created_at")
        var createdAt: String = "",
        @SerializedName("user_name")
        var userName: String = "",
        @SerializedName("image")
        var image: Int = 0,
        @SerializedName("is_active")
        var isActive: Int = 1
    ) {

        fun toJson() = Gson().toJson(this)

        fun toObject(jsonStr: String) = Gson().fromJson(jsonStr, Account::class.java)

    }

    data class RegisterModel(
        @SerializedName("email")
        var email: String,
        @SerializedName("phoneNumber")
        var phoneNumber: String,
        @SerializedName("password")
        var password: String
    )

    data class LoginModel(
        @SerializedName("email")
        var email: String = "",
        @SerializedName("password")
        var password: String = ""
    )
}