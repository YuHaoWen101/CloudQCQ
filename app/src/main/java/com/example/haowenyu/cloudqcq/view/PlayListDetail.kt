package com.example.haowenyu.cloudqcq.view

import android.app.Activity
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

class PlayListDetail:Activity(),Contract.Playlist_detail{
    val presenter= Presenter()
    lateinit var id:String
    private var list: MutableList<Item> = arrayListOf()
    private lateinit var itemManager: ItemManager
    private lateinit var itemAdapter: ItemAdapter

    override fun getplaydetail(detail: playlist_detail?) {

    for (i in 0 until detail?.playlist?.tracks?.size!!){
        list.add(Song_detai(
            detail.playlist.tracks[i].al.picUrl,
            detail.playlist.tracks[i].al.name,
            detail.playlist.tracks[i].ar[0].name,
            detail.playlist.tracks[i].al.id

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
            id = b.getString("id")
            presenter.getlistdetail(id,this)
        }else{Toast.makeText(this,"空的id",Toast.LENGTH_SHORT).show() }


    }
    fun init(){
        songlist.apply {
            itemManager = ItemManager(list)
            itemAdapter = ItemAdapter(itemManager)
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }
}
