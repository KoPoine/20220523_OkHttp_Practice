package com.neppplus.a20220523_okhttp_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.a20220523_okhttp_practice.adapters.TopicRecyclerAdapter
import com.neppplus.a20220523_okhttp_practice.databinding.ActivityMainBinding
import com.neppplus.a20220523_okhttp_practice.models.TopicData
import com.neppplus.a20220523_okhttp_practice.utils.ContextUtil
import com.neppplus.a20220523_okhttp_practice.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mTopicAdapter : TopicRecyclerAdapter

    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        backBtn.visibility = View.GONE
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.logoutBtn.setOnClickListener {
            ContextUtil.clear(mContext)

            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    override fun setValues() {
        getTopicListFromServer()

        profileBtn.visibility = View.VISIBLE

        mTopicAdapter = TopicRecyclerAdapter(mContext,mTopicList)
        binding.topicRecyclerView.adapter = mTopicAdapter
        binding.topicRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getTopicListFromServer() {
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val dataObj = jsonObj.getJSONObject("data")
                val topicArr = dataObj.getJSONArray("topics")

                for (i in 0 until topicArr.length()) {
//                    [   ] => { }, { }, .... 순서에 맞는  { }를 변수에 담자
//                    JSON 파싱의 {  }  => JSONArray => JSONObject로 추출
                    val topicObj = topicArr.getJSONObject(i)

                    Log.d("받아낸 주제", topicObj.toString())

//                    val topicData = TopicData()
//
//                    topicData.id = topicObj.getInt("id")
//                    topicData.title = topicObj.getString("title")
//                    topicData.imageURL = topicObj.getString("img_url")
//                    topicData.replyCount = topicObj.getInt("reply_count")

//                    TopicData 변수를 생성 => topicObj 대입
                    val topicData = TopicData.getTopicDataFromJson(topicObj)

//                    => ArrayList 추가
                    mTopicList.add(topicData)

                    runOnUiThread {
                        mTopicAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}




















