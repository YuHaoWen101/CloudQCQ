package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemAdapter
import cn.edu.twt.retrox.recyclerviewdsl.ItemManager
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.Presenter
import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.view.items.Song_detai

class PlayListDetail:Activity(),Contract.Playlist_detail{
    val presenter= Presenter()
    lateinit var id:String
    private var list: MutableList<Item> = arrayListOf()
    private lateinit var itemManager: ItemManager
    private lateinit var itemAdapter: ItemAdapter

    override fun getplaydetail(detail: playlist_detail?) {
        detail?.privileges?.forEach {
            list.add(Song_detai(it.al.picUrl,it.al.name,it.ar.name,it.id))//强加al不行
        }
    }

    override fun onerror(message: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b:Bundle? = intent.extras
        if (b?.getString("id") != null){
            id = b.getString("id")
            presenter.getlistdetail(id,this)
        }else{Toast.makeText(this,"空的id",Toast.LENGTH_SHORT).show() }


    }
}
