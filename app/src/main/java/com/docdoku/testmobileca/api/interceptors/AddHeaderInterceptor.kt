package com.docdoku.testmobileca.api.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import java.io.IOException

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * This class is used to intercept the request and add a header to it.
 */

class AddHeaderInterceptor: Interceptor {

    /**
     * This method is used to intercept the request and add a header to it.
     * We can also make necessary changes to the request here before sending it to the server.
     *
     * @param chain
     * @return Response
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val reqBuilder: Builder = original.newBuilder()
        reqBuilder.header("Content-Type", "application/json")
        val request: Request = reqBuilder
            .url(original.url.toString())
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}