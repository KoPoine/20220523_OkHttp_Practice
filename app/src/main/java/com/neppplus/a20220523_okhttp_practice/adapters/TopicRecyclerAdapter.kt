package com.neppplus.a20220523_okhttp_practice.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.a20220523_okhttp_practice.DetailTopicActivity
import com.neppplus.a20220523_okhttp_practice.R
import com.neppplus.a20220523_okhttp_practice.databinding.TopicListItemBinding
import com.neppplus.a20220523_okhttp_practice.models.TopicData

class TopicRecyclerAdapter(val mContext : Context, val mList : List<TopicData>) : RecyclerView.Adapter<TopicRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : TopicData) {
            val backgroundImg = itemView.findViewById<ImageView>(R.id.backgroundImg)
            val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
            val replyCountTxt = itemView.findViewById<TextView>(R.id.replyCountTxt)

            Glide.with(mContext).load(item.imageURL).into(backgroundImg)
            titleTxt.text = item.title
            replyCountTxt.text = "현재 댓글 갯수 : ${item.replyCount}개"

            itemView.setOnClickListener {
                val myIntent = Intent(mContext, DetailTopicActivity::class.java)
                myIntent.putExtra("topicData", item)
                mContext.startActivity(myIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.topic_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}