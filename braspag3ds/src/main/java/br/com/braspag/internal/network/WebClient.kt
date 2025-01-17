package br.com.braspag.internal.network

import br.com.braspag.app.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class WebClient(url: String) {

    companion object {
        private const val TIMEOUT = 45L
    }

    private val retrofit = Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
    private val httpClient = OkHttpClient().newBuilder()

    fun <T> createService(service: Class<T>): T {
        val logger = HttpLoggingInterceptor()

        logger.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val client = httpClient
            .addInterceptor(logger)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        return retrofit
            .client(client)
            .build()
            .create(service)
    }
}
