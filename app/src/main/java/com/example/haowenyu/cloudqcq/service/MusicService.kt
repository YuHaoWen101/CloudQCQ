package com.example.haowenyu.cloudqcq.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MusicService : Service() {
    private var player: MediaPlayer = MediaPlayer()

    override fun onBind(intent: Intent): IBinder? {
        return MyBinder()
    }

    override fun onCreate() {
        super.onCreate()

        //这里只执行一次，用于准备播放器
        /*
        try {
            player!!.setDataSource(path)
            //准备资源
            player!!.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        Log.e("服务", "准备播放音乐")*/
    }

    //该方法包含关于歌曲的操作
    inner class MyBinder : Binder() {
        fun play(songurl:String){
            player.reset()
            player.setDataSource(songurl)
            player.prepare()
            player.start()
        }
        //判断是否处于播放状态
        val isPlaying: Boolean
            get() = player.isPlaying

        //返回歌曲的长度，单位为毫秒
        val duration: Int
            get() = player.duration

        //返回歌曲目前的进度，单位为毫秒
        val currenPostion: Int
            get() = player.currentPosition

        //播放或暂停歌曲
        fun play() {
            if (player.isPlaying) {
                player.pause()
            } else {
                player.start()
            }
            Log.e("服务", "播放音乐")
        }

        //设置歌曲播放的进度，单位为毫秒
        fun seekTo(mesc: Int) {
            player.seekTo(mesc)
        }
    }
}