package com.example.haowenyu.cloudqcq.view

import android.animation.ObjectAnimator
import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.Presenter
import com.example.haowenyu.cloudqcq.R
import kotlinx.android.synthetic.main.song_play.*
import java.io.IOException

class SongPlay: Activity(),Contract.Song_play{
    lateinit private var mediaPlayer:MediaPlayer
    var myPlayer = com.example.haowenyu.cloudqcq.common.MyMusicPlayer
    var Songurl:String? = null
    lateinit var animator:ObjectAnimator
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
        animator = ObjectAnimator.ofFloat(XC, "rotation", 0f, 360f).setDuration(40000)
        init()
        animator.apply {
            setInterpolator (LinearInterpolator())
            repeatCount = 10000000 //改
        }
    }

    private fun initplayer(url:String?){
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }



    private fun init(){
        mediaPlayer = myPlayer.player.mediaPlayer
        mediaPlayer.reset()
        val b:Bundle? = intent.extras
        if (b?.getString("song_id") != null){
            var id = b.getString("song_id")!!
            val url = b.getString("pic_url")
            Glide.with(this).load(url).into(XC)
            presenter.getSongUrl(id,this)
        }else{
            Toast.makeText(this,"空的id", Toast.LENGTH_SHORT).show() }
        previous.setOnClickListener{

        }

        btn_pause.setOnClickListener{
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
                animator.pause()
            }else {
                mediaPlayer.start()
                animator.start()
            }
        }
        next.setOnClickListener {

        }




    }

    override fun onDestroy() {
        super.onDestroy()
        //mediaPlayer.release()
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
