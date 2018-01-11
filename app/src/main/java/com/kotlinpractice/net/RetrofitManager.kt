package com.kotlinpractice.net

import com.hazz.kotlinmvp.api.ApiService
import com.hazz.kotlinmvp.api.UriConstant
import com.kotlinpractice.MyApplication
import com.kotlinpractice.utils.Preference
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by jifeng.liu on 18/1/11.
 */
object RetrofitManager {
    private var retrofit : Retrofit? = null
    private var client : OkHttpClient? = null

    private var token:String by Preference("token","")
    val service:ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }


    private fun getRetrofit():Retrofit?{
        if(retrofit == null){
            synchronized(RetrofitManager::class.java){
                if (retrofit == null){
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                    val cacheFile = File(MyApplication.context.cacheDir,"cache")
                    val cache = Cache(cacheFile,1024*1024*50)
                    client = OkHttpClient.Builder()
                            .addInterceptor(addQueryParameterInterceptor())
                            .addInterceptor(addHeaderInterceptor())
                            .addInterceptor(httpLoggingInterceptor)
                            .cache(cache)
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .build()

                    retrofit = Retrofit.Builder()
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(UriConstant.BASE_URL)
                            .client(client)
                            .build()
                }
            }
        }
        return retrofit
    }

    private fun addQueryParameterInterceptor():Interceptor{
        return Interceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            val request : Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("phoneSystem", "")
                    .addQueryParameter("phoneModel", "")
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    private fun addHeaderInterceptor():Interceptor{
        return Interceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token", token)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}