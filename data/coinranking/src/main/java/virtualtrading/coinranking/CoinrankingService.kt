package virtualtrading.coinranking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinrankingService {

    @GET("/coins")
    suspend fun getCoins(
        @Query("orderBy") orderBy: String? = null,
        @Query("limit") amount: Int = 50,
        @Query("uuids") ids: List<String>? = null,
    ): GetCoinsDTO

    @GET("/coin/{uuid}")
    suspend fun getCoinById(@Path("uuid") id: String): GetCoinDTO

    @GET("/search-suggestions")
    suspend fun searchCoin(@Query("query") query: String): SearchCoinDTO

}

@ExperimentalSerializationApi
fun CoinrankingService(): CoinrankingService {

    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = true
    }

    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val authenticatedRequest =
            chain.request().newBuilder().addHeader("X-RapidAPI-Key", "731a2443admshd3d00fee805369cp152d9bjsn955eb13dd49b").build()
        chain.proceed(authenticatedRequest)

    }.addInterceptor(httpLoggingInterceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://coinranking1.p.rapidapi.com/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
        .client(okHttpClient)
        .build()
    return retrofit.create(CoinrankingService::class.java)
}
