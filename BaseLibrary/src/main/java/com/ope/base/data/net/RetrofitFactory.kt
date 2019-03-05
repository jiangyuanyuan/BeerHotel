package com.ope.base.data.net

import com.ope.base.DEBUG_API_BASE_URL
import com.ope.base.KEY_TOKEN
import com.google.gson.JsonParser

import com.ope.base.data.notice.ErrorNotice
import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor(){

    /**
     * 单例模式
     */
    companion object {
        val instance : RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit : Retrofit
    private val interceptor: Interceptor

    //初始化
    init {
        //通用拦截
        interceptor = Interceptor { chain ->

            val request = chain.request()
            val builder = request.newBuilder()
                    .addHeader("Content_Type", "application/json")
                    .addHeader("charset","UTF-8")
            if (Hawk.get<String>(KEY_TOKEN) != null){
                builder.addHeader("Authorization", Hawk.get<String>(KEY_TOKEN))
            }
            val response = chain.proceed(builder.build())
            val bytes = response.body()!!.bytes() ?: "".toByteArray()
            val build = response.newBuilder()
                    .body(ResponseBody.create(MediaType.parse("UTF-8"), bytes)).build()
            try {
                val returnData = JsonParser().parse(String(bytes)).asJsonObject
                val responseCode = returnData.get("responseCode").asString.toInt()
                when(responseCode){
                    112 -> { // 另一处登录
                        ErrorNotice.INSTANCE.notifyError(112,"另一端登录")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            build
        }

        //Retrofit实例化
        retrofit = Retrofit.Builder()
                .baseUrl(DEBUG_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /*
        OKHttp创建
    */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor) // todo 这里一定要这个拦截器先添加
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build()
    }

    /*
        日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /*
        具体服务实例化
     */
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }


}