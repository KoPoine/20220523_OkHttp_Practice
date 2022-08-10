package com.neppplus.a20220523_okhttp_practice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.a20220523_okhttp_practice.R
import com.neppplus.a20220523_okhttp_practice.databinding.TopicListItemBinding
import com.neppplus.a20220523_okhttp_practice.models.ReplyData

class ReplyRecyclerAdapter(
    val mContext: Context,
    val mList: List<ReplyData>
) : RecyclerView.Adapter<ReplyRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ReplyData) {
            val writerNickTxt = itemView.findViewById<TextView>(R.id.writerNickTxt)
            val selectSideTxt = itemView.findViewById<TextView>(R.id.selectSideTxt)
            val createAtTxt = itemView.findViewById<TextView>(R.id.createAtTxt)
            val contentTxt = itemView.findViewById<TextView>(R.id.contentTxt)
            val replyCountTxt = itemView.findViewById<TextView>(R.id.replyCountTxt)
            val likeCountTxt = itemView.findViewById<TextView>(R.id.likeCountTxt)
            val dislikeCountTxt = itemView.findViewById<TextView>(R.id.dislikeCountTxt)

            writerNickTxt.text = item.user.nickname
            selectSideTxt.text = "(${item.selectedSide.title})"
            createAtTxt.text = item.createdAt
            contentTxt.text = item.content
            replyCountTxt.text = "답글 : ${item.replyCount}개"
            likeCountTxt.text = "좋아요 : ${item.likeCount}개"
            dislikeCountTxt.text = "싫어요 : ${item.dislikeCount}개"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.reply_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
