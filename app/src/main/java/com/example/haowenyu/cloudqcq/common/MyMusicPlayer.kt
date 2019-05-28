package com.example.haowenyu.cloudqcq.common

import android.media.MediaPlayer

class MyMusicPlayer{
    companion object {
        val player = Holder.INSTANCE
    }
    private object Holder{
        val INSTANCE =MyMusicPlayer()
    }
    var mediaPlayer = MediaPlayer()
}