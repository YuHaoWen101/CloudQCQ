package com.example.haowenyu.cloudqcq.view

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle

import android.support.v7.widget.LinearLayoutManager
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemAdapter
import cn.edu.twt.retrox.recyclerviewdsl.ItemManager
import com.example.haowenyu.cloudqcq.Contract
import com.example.haowenyu.cloudqcq.LoginPresenter
import com.example.haowenyu.cloudqcq.R
import com.example.haowenyu.cloudqcq.datamodel.userplayerlist
import com.example.haowenyu.cloudqcq.view.items.playerlists
import kotlinx.android.synthetic.main.activity_playerlist.*

class PlayerlistActivity: Activity(),Contract.PlayerlistView{
    private var listlist: MutableList<Item> = arrayListOf()
    private lateinit var itemManager: ItemManager
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val presenter = LoginPresenter()
        val id:String = intent.getStringExtra("userid")
        presenter.getplaylist(id,this)

    }







    override fun getplaylist(userplayerlist: userplayerlist) {

        listlist.add(playerlists("本地音乐","455"))
        listlist.add(playerlists("下载管理","(460)"))
        listlist.add(playerlists("最近播放","(110)"))

        userplayerlist.playlist.forEach {
            val item = playerlists(it.name,it.trackCount.toString())
            listlist.add(item)
        }
    playlist.apply {
        itemManager = ItemManager(listlist)
        itemAdapter = ItemAdapter(itemManager)
        adapter = itemAdapter
        layoutManager = LinearLayoutManager(this.context)
    }
    }

    override fun onerror(message: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playerlist)
        //val userid:String = intent.getBundleExtra("iuserid").toString()



    }
}