package com.docdoku.testmobileca.api.interceptors

import android.content.Context
import com.docdoku.testmobileca.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * This class is used to intercept the request and return a mock response
 */

class ServerInterceptor(val context: Context): Interceptor {

    /**
     * This method is used to intercept the request and return a mock response from the assets folder
     * if the BuildConfig.USE_MOCK is true
     *
     * @param chain
     * @return Response
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = if(BuildConfig.USE_MOCK) {
            val responseString: String = getJsonDataFromAsset(context).orEmpty()
            Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(responseString.toByteArray().toResponseBody("application/json".toMediaType()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            chain.proceed(chain.request())
        }
        return response
    }

    /**
     * This method is used to read the json file containing mocked data from the assets folder
     *
     * @param context
     * @return String
     */
    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            // Append the json extension
            jsonString = context.assets.open("banks.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}