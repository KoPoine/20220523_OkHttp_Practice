package com.neppplus.a20220523_okhttp_practice

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.neppplus.a20220523_okhttp_practice.adapters.ReplyRecyclerAdapter
import com.neppplus.a20220523_okhttp_practice.databinding.ActivityDetailTopicBinding
import com.neppplus.a20220523_okhttp_practice.models.ReplyData
import com.neppplus.a20220523_okhttp_practice.models.TopicData
import com.neppplus.a20220523_okhttp_practice.utils.ServerUtil
import org.json.JSONObject

class DetailTopicActivity : BaseActivity() {

    lateinit var binding : ActivityDetailTopicBinding

    var mTopicData = TopicData()

    val mReplyList = ArrayList<ReplyData>()

    lateinit var mReplyAdapter : ReplyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_topic)
        mTopicData = intent.getSerializableExtra("topicData") as TopicData
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        vote1Btn 클릭 > 해당 진영의 id값을 찾아서 거기에 투표
//        서버에 전달 > API 활용
        binding.vote1Btn.setOnClickListener {
            voteSide (mTopicData.sideList[0].id)
        }

        binding.vote2Btn.setOnClickListener {
            voteSide(mTopicData.sideList[1].id)
        }
    }

    override fun setValues() {

        setTopicDataToUi()

        getTopicDetailFromServer()

        mReplyAdapter = ReplyRecyclerAdapter(mContext, mReplyList)
        binding.replyRecyclerView.adapter = mReplyAdapter
        binding.replyRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    fun getTopicDetailFromServer () {
        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, "NEW", object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

                val topicData = TopicData.getTopicDataFromJson(topicObj)

                mTopicData = topicData

                runOnUiThread {
                    setTopicDataToUi()
                }

//                댓글 목록도 별도로 파싱

                mReplyList.clear()

                val repliesArr = topicObj.getJSONArray("replies")

                for (i in 0 until repliesArr.length()) {
                    val replyObj = repliesArr.getJSONObject(i)

                    val replyData = ReplyData.getReplyDataFromJson(replyObj)

                    mReplyList.add(replyData)
                }
                runOnUiThread {
                    mReplyAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun setTopicDataToUi () {
//        토론 주제에 대한 데이터들을 UI에 반영하는 함수
//        화면 초기 진입 실행 + 서버에서 다시 받아왔을때도 실행

        binding.titleTxt.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.backgroundImg)

        binding.side1Txt.text = mTopicData.sideList[0].title
        binding.side2Txt.text = mTopicData.sideList[1].title

        binding.vote1CountTxt.text = "${mTopicData.sideList[0].voteCount}표"
        binding.vote2CountTxt.text = "${mTopicData.sideList[1].voteCount}표"
    }

    fun voteSide(sideId : Int) {
        //            서버의 투표 API 호출
        ServerUtil.postRequestVote(mContext, sideId, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val code = jsonObj.getInt("code")
                val message = jsonObj.getString("message")

                runOnUiThread {
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                    if (code == 200) {
                        val dataObj = jsonObj.getJSONObject("data")
                        val topicObj = dataObj.getJSONObject("topic")

                        mTopicData = TopicData.getTopicDataFromJson(topicObj)

                        setTopicDataToUi()
                    }
                }
            }
        })
    }
}
















