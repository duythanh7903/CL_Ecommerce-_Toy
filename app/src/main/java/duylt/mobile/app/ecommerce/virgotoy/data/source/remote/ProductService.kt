package duylt.mobile.app.ecommerce.virgotoy.data.source.remote

import duylt.mobile.app.ecommerce.virgotoy.domain.model.Product
import retrofit2.http.GET

interface ProductService {
    @GET("customer/products/all")
    suspend fun getAllProducts(): MutableList<Product>
}