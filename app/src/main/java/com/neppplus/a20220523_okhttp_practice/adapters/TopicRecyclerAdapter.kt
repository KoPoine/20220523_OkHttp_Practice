package com.neppplus.a20220523_okhttp_practice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.a20220523_okhttp_practice.R
import com.neppplus.a20220523_okhttp_practice.databinding.TopicListItemBinding
import com.neppplus.a20220523_okhttp_practice.models.TopicData

class TopicRecyclerAdapter(val mContext : Context, val mList : List<TopicData>) : RecyclerView.Adapter<TopicRecyclerAdapter.MyViewHolder>() {

    lateinit var binding : TopicListItemBinding

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : TopicData) {
//            val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
//            val replyCountTxt = itemView.findViewById<TextView>(R.id.replyCountTxt)
//            val backgroundImg = itemView.findViewById<ImageView>(R.id.backgroundImg)

            binding.titleTxt.text = item.title
            binding.replyCountTxt.text = "${item.replyCount}명 참여중"
            Glide.with(mContext)
                .load(item.imageUrl)
                .into(binding.backgroundImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.topic_list_item, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}