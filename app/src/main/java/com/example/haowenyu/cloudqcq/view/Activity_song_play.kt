package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.Presenter
import com.example.haowenyu.cloudqcq.R
import kotlinx.android.synthetic.main.song_play.*
import org.jetbrains.anko.sdk25.coroutines.onTouch
import java.io.IOException

class Activity_song_play: Activity(),Contract.Song_play{
    private var mediaPlayer = MediaPlayer()
    var Songurl:String? = null

    override fun getUrl(surl: String?){
        Songurl = surl
        initplayer(Songurl)
    }

    override fun onerror(message: String?) {

    }

    private val presenter = Presenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_play)
        init()

    }


    private fun initplayer(url:String?){
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
        }catch (e:IOException){
            e.printStackTrace()
        }

    }



    private fun init(){
        val b:Bundle? = intent.extras
        if (b?.getString("song_id") != null){
            var id = b.getString("song_id")!!
            presenter.getSongUrl(id,this)
        }else{
            Toast.makeText(this,"空的id", Toast.LENGTH_SHORT).show() }
        previous.setOnClickListener{

        }

        btn_pause.setOnClickListener{
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }else mediaPlayer.start()
        }
        next.setOnClickListener {

        }
    }



    internal inner class SeekBarThread : Runnable {
        //进度条

        val handler = Handler()
        override fun run() {
            while (mediaPlayer.isPlaying== true) {
                // 将SeekBar位置设置到当前播放位置
                handler.sendEmptyMessage(mediaPlayer.currentPosition)
                try {
                    // 每100毫秒更新一次位置
                    Thread.sleep(80)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }

        }
    }
}