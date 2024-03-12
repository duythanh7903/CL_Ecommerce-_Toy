package duylt.mobile.app.ecommerce.virgotoy.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import duylt.mobile.app.ecommerce.virgotoy.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {

        private fun getRetrofitBuilder() =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

        private val gson: Gson = GsonBuilder().setDateFormat("dd/MM/yyyy").create()

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val productService: ProductService = getRetrofitBuilder()
            .create(ProductService::class.java)

        val categoryService: CategoryService = getRetrofitBuilder()
            .create(CategoryService::class.java)

        val accountService: AccountService = getRetrofitBuilder()
            .create(AccountService::class.java)
    }
}