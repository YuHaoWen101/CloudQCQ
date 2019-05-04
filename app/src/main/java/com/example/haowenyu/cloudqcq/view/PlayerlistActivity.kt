package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemAdapter
import cn.edu.twt.retrox.recyclerviewdsl.ItemManager
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.LoginPresenter
import com.example.haowenyu.cloudqcq.R
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist
import com.example.haowenyu.cloudqcq.view.items.playerlists
import com.example.haowenyu.cloudqcq.view.items.playlist_info
import kotlinx.android.synthetic.main.activity_playerlist.*

class PlayerlistActivity: Activity(),Contract.PlayerlistView{
    private var listlist: MutableList<Item> = arrayListOf()
    private lateinit var itemManager: ItemManager
    private lateinit var itemAdapter: ItemAdapter
    var presenter:LoginPresenter = LoginPresenter()






    override fun getplaylist(userplayerlist: userplayerlist) {

        listlist.add(playerlists("本地音乐","455"))
        listlist.add(playerlists("下载管理","(460)"))
        listlist.add(playerlists("最近播放","(110)"))

        userplayerlist.playlist.forEach {
            val item = playlist_info(it.name,it.trackCount.toString(),it.coverImgUrl,it.id)
            listlist.add(item)
        }


    }

    override fun onerror(message: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playerlist)
        //val userid:String = intent.getBundleExtra("iuserid").toString()

        val id = "568596482"//intent.getStringExtra("userid")
        presenter.getplaylist(id,this)
        playlist.apply {
            itemManager = ItemManager(listlist)
            itemAdapter = ItemAdapter(itemManager)
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

    }
}