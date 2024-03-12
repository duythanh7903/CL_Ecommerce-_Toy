package duylt.mobile.app.ecommerce.virgotoy.data.source.remote

import duylt.mobile.app.ecommerce.virgotoy.domain.model.MessageResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("customer/account/register")
    suspend fun registerAccount(@Body registerModel: MessageResponse.RegisterModel): MessageResponse

    @POST("customer/account/login")
    suspend fun loginAccount(@Body login: MessageResponse.LoginModel): MessageResponse
}