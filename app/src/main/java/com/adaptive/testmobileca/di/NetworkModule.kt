package com.adaptive.testmobileca.di

import android.content.Context
import com.adaptive.testmobileca.api.interceptors.AddHeaderInterceptor
import com.adaptive.testmobileca.api.ApiService
import com.adaptive.testmobileca.api.interceptors.ServerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This class is used to provide the ApiService using Hilt dependency injection
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiService(
        @ApplicationContext context: Context
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://cdf-test-mobile-default-rtdb.europe-west1.firebasedatabase.app/")
            .client(buildHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    private fun buildHttpClient(context: Context) = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AddHeaderInterceptor())
            .addInterceptor(ServerInterceptor(context))
            .readTimeout((5 * 60).toLong(), TimeUnit.SECONDS)
            .writeTimeout((5 * 60).toLong(), TimeUnit.SECONDS)
            .build()
}