package com.example.haowenyu.cloudqcq

import com.example.haowenyu.cloudqcq.datamodel.Song
import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.datamodel.user
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface Api{

    @GET("/login/cellphone")
    fun sighin(@Query("phone") phone: String?, @Query("password") password: String?): Call<user>

    @GET("/user/playlist")
    fun getPlaylist(@Query("uid")id:String?):Call<userplayerlist>

    @GET("/playlist/detail")
    fun getListDetail(@Query("id") id: String?): Call<playlist_detail>

    @GET(" /song/url")
    fun getSongUrl(@Query("id")id:String):Call<Song>

}