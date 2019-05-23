package com.example.haowenyu.cloudqcq.common

import android.media.MediaPlayer

class myPlayer{
    companion object {
        val player = Holder.INSTANCE
    }
    private object Holder{
        val INSTANCE =myPlayer()
    }
    var mediaPlayer = MediaPlayer()

}