package com.example.data.di

import android.content.Context
import com.example.data.BuildConfig
import com.example.data.dataSource.remote.api.ImageApi
import com.example.data.util.ApiConfigs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.time.toJavaDuration

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val requestUrl = request.url
            val url = requestUrl.newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .addQueryParameter("image_type", ApiConfigs.IMAGE_TYPE)
                .build()

            val modifiedRequest = request.newBuilder()
                .url(url)
                .build()
            chain.proceed(modifiedRequest)
        }
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context) =
        Cache(context.cacheDir, ApiConfigs.cacheSize)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        authenticationInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(loggingInterceptor)
            }
        }
        .addInterceptor(authenticationInterceptor)
        .cache(cache)
        .connectTimeout(ApiConfigs.Timeouts.connect.toJavaDuration())
        .writeTimeout(ApiConfigs.Timeouts.write.toJavaDuration())
        .readTimeout(ApiConfigs.Timeouts.read.toJavaDuration())
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitApiService(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): ImageApi =
        Retrofit.Builder()
            .baseUrl(ApiConfigs.BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(ImageApi::class.java)
}