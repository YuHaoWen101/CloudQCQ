package com.example.haowenyu.cloudqcq.view.items

import org.jetbrains.anko.layoutInflater

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemController
import com.bumptech.glide.Glide
import com.example.haowenyu.cloudqcq.R
import com.example.haowenyu.cloudqcq.datamodel.playlist_detail
import com.example.haowenyu.cloudqcq.view.Activity_song_play
import com.example.haowenyu.cloudqcq.view.PlayListDetail

import kotlinx.android.synthetic.main.recycle_playerlist_common_download.view.*
import kotlinx.android.synthetic.main.recycle_playerlist_list.view.*
import kotlinx.android.synthetic.main.recycle_song_info.view.*


class playerlists(val text: String?, val num: String?) : Item {
    override val controller: ItemController = Companion

    companion object : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val inflater = parent.context.layoutInflater
            val view = inflater.inflate(R.layout.recycle_playerlist_common_download, parent, false)
            return ViewHolder(

                view,
                view.base_download_item_icon,
                view.base_download_item_title,
                view.base_download_item_count
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
            holder as ViewHolder
            item as playerlists
            holder.title.text = item.text
            holder.count.text = item.num
            when (holder.title.text) {
                "本地音乐" -> {
                    Glide.with(holder.view)
                        .load(R.drawable.local)
                        .into(holder.imageView)
                    //holder.isOn.visibility = View.VISIBLE
                }
                "下载管理" -> Glide.with(holder.view)
                    .load(R.drawable.download)
                    .into(holder.imageView)
                "最近播放" -> Glide.with(holder.view)
                    .load(R.drawable.recent)
                    .into(holder.imageView)
            }
        }

        class ViewHolder(
            val view: View,
            val imageView: ImageView,
            val title: TextView,
            val count: TextView
        ) : RecyclerView.ViewHolder(view)

    }
}

class playlist_info(val text: String?, val count: String?, val image: String?, val id: String?) : Item {
    override val controller: ItemController =Companion


    companion object : ItemController {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
            holder as ViewHolder
            item as playlist_info
            holder.title.text = item.text + "(" + item.count + "首)"
            Glide.with(holder.view).load(item.image).into(holder.image)
            holder.view.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", item.id)
                val intent = Intent(holder.view.context, PlayListDetail::class.java)
                intent.putExtras(bundle)
                holder.view.context.startActivity(intent)
            }
            holder.end.setOnClickListener {

                //额外操作
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val inflater = parent.context.layoutInflater
            val view = inflater.inflate(R.layout.recycle_playerlist_list, parent, false)
            return ViewHolder(
                view,
                view.base_list_item_icon,
                view.base_list_item_title,
                view.playlist_button
            )
        }

    }

    class ViewHolder(
        val view: View,
        val image: ImageView,
        val title: TextView,
        val end: ImageView
    ) : RecyclerView.ViewHolder(view)
}


class Song_detai(
    val img: String?,
    val song_name: String?,
    val song_author: String?,
    val song_id: String,
    val playListDetail: PlayListDetail
) : Item {
    override val controller: ItemController = Companion

    companion object : ItemController {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
        holder as ViewHolder
        item as Song_detai
        holder.Song_name.text = item.song_name
        holder.Song_author.text = item.song_author
        Glide.with(holder.view).load(item.img).into(holder.Status)
        holder.view.setOnClickListener {
            item.playListDetail.getOnclickid(item.song_id)
            /*以后改
            val bundle = Bundle()
            bundle.putString("song_id",item.song_id) //换播放界面
            val intent = Intent(holder.view.context,Activity_song_play::class.java)
            intent.putExtras(bundle)
            holder.view.context.startActivity(intent)*/

            //播放
        }

        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val inflater = parent.context.layoutInflater
            val view = inflater.inflate(R.layout.recycle_song_info, parent, false)
            return ViewHolder(
            view,
            view.song_status,
            view.song_name,
            view.song_author,
            view.song_end
            )
        }
    }

    class ViewHolder(
        val view: View,
        val Status: ImageView,
        val Song_name: TextView,
        val Song_author: TextView,
        val endofsom: ImageView
    ) : RecyclerView.ViewHolder(view)
}