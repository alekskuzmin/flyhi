package com.alekskuzmin.flyhi.core.remote

import com.alekskuzmin.flyhi.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

    fun createService(
        gson: Gson,
        baseUrl: String,
        dispatcher: Dispatcher
    ): FlyHiService {
        val okHttpClient = createOkHttpClient(
            createLoggingInterceptor(),
            dispatcher

        )
        return createService(okHttpClient, gson, baseUrl).create(FlyHiService::class.java)
    }

    private fun createService(
        okHttpClient: OkHttpClient,
        gson: Gson,
        baseUrl: String
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        retrofit.baseUrl(baseUrl)
        return retrofit.build()
    }

    private fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dispatcher: Dispatcher
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .followRedirects(false)
            .dispatcher(dispatcher)

        return builder.build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY
        return logging
    }
}