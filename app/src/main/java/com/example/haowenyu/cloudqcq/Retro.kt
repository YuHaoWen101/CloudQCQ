package com.example.haowenyu.cloudqcq

import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.datamodel.user
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class  Retro{
    companion object {
        val retro:Retro =Holder.INSTANCE
    }
    private object Holder{
        val INSTANCE = Retro()
    }
    private var client = OkHttpClient.Builder()

        .addInterceptor(HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://172.23.23.228:3000")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().setLenient().create()))
        .build()

    private var httpInterface = retrofit.create(Api::class.java)

    fun login(phone:String,password:String): Call<user> {
       return httpInterface.sighin(phone,password)
    }
    fun getplaylist(id:String):Call<userplayerlist>{
        return httpInterface.getPlaylist(id)
    }
    fun getlistdetail(id:String):Call<playlist_detail>
    {
        return httpInterface.getListDetail(id)
    }
}
