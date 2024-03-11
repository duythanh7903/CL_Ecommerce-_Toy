package duylt.mobile.app.ecommerce.virgotoy.data.source.remote

import duylt.mobile.app.ecommerce.virgotoy.domain.model.Category
import retrofit2.http.GET

interface CategoryService {
    @GET("customer/categories/all")
    suspend fun getAllCategories(): MutableList<Category>
}