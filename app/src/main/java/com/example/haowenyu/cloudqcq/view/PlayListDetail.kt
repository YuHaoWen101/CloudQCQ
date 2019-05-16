package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemAdapter
import cn.edu.twt.retrox.recyclerviewdsl.ItemManager
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.Presenter
import com.example.haowenyu.cloudqcq.R
import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.view.items.Song_detai
import kotlinx.android.synthetic.main.activity_songlist.*

import kotlinx.coroutines.launch
import java.io.IOException

class PlayListDetail:Activity(),Contract.Playlist_detail{
    val presenter= Presenter()
    lateinit var id:String
    private var list: MutableList<Item> = arrayListOf()
    private lateinit var itemManager: ItemManager
    private lateinit var itemAdapter: ItemAdapter
    lateinit var mdetail:playlist_detail
    private var player = MediaPlayer()
    fun getOnclickid(song_id:String){
     presenter.getSongUrllist(song_id,this)
    }

    fun getsongurl(url:String){
        initplayer(url)
    }

    private fun initplayer(url:String?){
        try {
            player.setDataSource(url)
            player.prepareAsync()
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
}
