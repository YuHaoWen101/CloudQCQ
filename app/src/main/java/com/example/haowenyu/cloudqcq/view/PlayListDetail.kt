package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.widget.SeekBar
import android.widget.Toast
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemAdapter
import cn.edu.twt.retrox.recyclerviewdsl.ItemManager
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.Presenter
import com.example.haowenyu.cloudqcq.R
import com.example.haowenyu.cloudqcq.R.id.music_seekbar
import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.view.items.Song_detai
import kotlinx.android.synthetic.main.activity_songlist.*

import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
private val REFRESH_TIME = 1
private val mPosOverride:Long = -1
private var isSeekBarChanging:Boolean =false
lateinit var player: MediaPlayer
class PlayListDetail:Activity(),Contract.Playlist_detail{
    val presenter= Presenter()
    private var idlist= arrayListOf<String>()
    private var currentPosition:Int = 0
    lateinit var id:String

    private var list: MutableList<Item> = arrayListOf()

    private lateinit var itemManager: ItemManager
    private lateinit var itemAdapter: ItemAdapter
    lateinit var mdetail:playlist_detail
    lateinit var  seekBar:SeekBar

    fun getOnclickid(song_id:String){
     presenter.getSongUrllist(song_id,this)
    }

    fun getsongurl(url:String){
        initplayer(url)
    }

    private fun initplayer(url:String?){
        try {
            if(player.isPlaying) {player.reset()}
            player.setDataSource(url)
            player.prepareAsync()
            /*player.setOnPreparedListener {

                fun onPrepared(mp:MediaPlayer){
                    mp.start()
                    mp.seekTo(currentPosition)
                    list_seekbar.max = player.duration

                }
            }*/

            seekBar.setOnSeekBarChangeListener(MySeekBar())
        }catch (e: IOException){
            e.printStackTrace()
        }
    }




    override fun getplaydetail(detail: playlist_detail?) {

    for (i in 0 until detail?.playlist?.tracks?.size!!){
        mdetail = detail
        list.add(Song_detai(
            detail.playlist.tracks[i].al.picUrl,
            detail.playlist.tracks[i].name,
            detail.playlist.tracks[i].ar[0].name,
            detail.privileges[i].id,
            this
        ))
        idlist.add(detail.privileges[i].id)
    }
    launch(kotlinx.coroutines.android.UI) {
        init()
    }

    }

    override fun onerror(message: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songlist)
        seekBar = findViewById(R.id.list_seekbar)
       // player = MediaPlayer()
        val b:Bundle? = intent.extras
        if (b?.getString("id") != null){
            id = b.getString("id")!!
            presenter.getlistdetail(id,this)
        }else{Toast.makeText(this,"空的id",Toast.LENGTH_SHORT).show() }
    }
    fun init(){
        songlist.apply {
            itemManager = ItemManager(list)
            itemAdapter = ItemAdapter(itemManager)
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this.context)
            song_detail_btn.setOnClickListener {

            }
            //music_total_time.setText(formatTime())
            previous.setOnClickListener{

            }

            btn_pause.setOnClickListener{
                if (player.isPlaying){
                    player.pause()
                }else player.start()
            }
            next.setOnClickListener {
            }
        }
    }

    fun formatTime(length:Long):String{
        var date= Date(length)
        val simpleDateFormatter =SimpleDateFormat("mm:ss")
        var TotalTime:String = simpleDateFormatter.format(date)
        return TotalTime
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
    class MySeekBar:SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            isSeekBarChanging = true
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            isSeekBarChanging = false
            player.seekTo(seekBar!!.progress)
        }

    }
}



