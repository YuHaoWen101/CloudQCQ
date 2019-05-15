package com.example.haowenyu.cloudqcq


import com.example.haowenyu.cloudqcq.datamodel.Song
import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.datamodel.user
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist
import com.example.haowenyu.cloudqcq.view.Activity_song_play
import com.example.haowenyu.cloudqcq.view.Activitylogin
import com.example.haowenyu.cloudqcq.view.PlayListDetail
import com.example.haowenyu.cloudqcq.view.PlayerlistActivity
import kotlinx.coroutines.*


import retrofit2.Call


class Presenter: Contract.Presenter {
    override fun getlistdetail(id: String, playlistDetail1: PlayListDetail) {
        val playListDetail:Call<playlist_detail> = Retro.retro.getlistdetail(id)
        launch {
            val detail = playListDetail.execute().body()
            launch {
                if (detail!=null)
                {
                    if (detail.code==400){playlistDetail1.onerror("参数错误")}
                    else playlistDetail1.getplaydetail(detail)
                }
            }
        }
    }



    override fun getplaylist(id: String, playerlistActivity:PlayerlistActivity ) {
        val playlist:Call<userplayerlist> = Retro.retro.getplaylist(id)
        launch {
            val list = playlist.execute().body()
            launch {
                if (list != null) {
                playerlistActivity.getplaylist(list)
                }else{
                    playerlistActivity.onerror("出错了")
                }
            }
        }

    }


    override fun doLogin(username: String, password: String, login:Activitylogin) {
        val iuser:Call<user> = Retro.retro.login(username,password)
        launch{
            val user = iuser.execute().body()
            launch {
                if (user?.code!=200) when(user!!.code){
                        501 -> login.loginFailed("账号不存在")
                        502 -> login.loginFailed("密码错误")
                        else ->{
                            login.loginFailed("就是出错了")
                        }
                    } else {
                    login.loginSuccess(user)}
            }
        }
    }

    fun getSongUrl(id: String,ac:Activity_song_play){
        val s:Call<Song> = Retro.retro.getSongUrl(id)//被坑了半天发现传的id有问题
        launch {
            val song =  s.execute().body()
            launch {

                ac.getUrl(song!!.data[0].url)
            }
        }
    }


}

        /*
        GlobalScope.launch {
           val user:user? = async { iuser.execute().body() }
           launch {
               if (user?.code==500){
                   login.loginFailed("登录出错了")
                   when(user.code){
                       501 -> Toast.makeText(login,"账号不存在",Toast.LENGTH_SHORT).show()
                       502 -> Toast.makeText(login,"密码错误",Toast.LENGTH_SHORT).show()
                       else ->{
                           Toast.makeText(login,"反正就是哪里出错了(确信",Toast.LENGTH_SHORT).show()
                       }
                   }
               }else {Toast.makeText(login,"登录成功",Toast.LENGTH_SHORT).show()
                   login.loginSuccess(com.example.haowenyu.cloudqcq.datamodel.user)}
           }
        }*/



/*
var login:Activitylogin = Activitylogin()
//去掉问号可能出问题来着

var iuser:Call<user> = Retro.retro.login(username,password)
iuser.enqueue(object : Callback<user> {
    override fun onResponse(call: Call<user>, response: Response<user>) {
        login.loginSuccess(response.body())
    }

    override fun onFailure(call: Call<user>, t: Throwable) {
        login.loginFailed("")
    }
})*/

/*
* MainScope().launch {
              val user: Deferred<user?> = async {iuser.execute().body() }
            launch {
                if (user.getCompleted()?.code==200){
                    login.loginFailed("登录出错了")
                    when(user.getCompleted()?.code){
                        501 -> Toast.makeText(login,"账号不存在",Toast.LENGTH_SHORT).show()
                        502 -> Toast.makeText(login,"密码错误",Toast.LENGTH_SHORT).show()
                        else ->{
                            Toast.makeText(login,"反正就是哪里出错了(确信",Toast.LENGTH_SHORT).show()
                        }
                    }
                }else {Toast.makeText(login,"登录成功",Toast.LENGTH_SHORT).show()
                    login.loginSuccess(user)}
            }
        }*/