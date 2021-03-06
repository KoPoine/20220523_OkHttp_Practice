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

        mTopicAdapter = TopicRecyclerAdapter(mContext,mTopicList)
        binding.topicRecyclerView.adapter = mTopicAdapter
        binding.topicRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    fun getTopicListFromServer() {
        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val dataObj = jsonObj.getJSONObject("data")
                val topicArr = dataObj.getJSONArray("topics")

                for (i in 0 until topicArr.length()) {
//                    [   ] => { }, { }, .... ????????? ??????  { }??? ????????? ??????
//                    JSON ????????? {  }  => JSONArray => JSONObject??? ??????
                    val topicObj = topicArr.getJSONObject(i)

                    Log.d("????????? ??????", topicObj.toString())

//                    TopicData ????????? ?????? => topicObj ??????
                    val topicData = TopicData.getTopicDataFromJson(topicObj)

//                    => ArrayList ??????
                    mTopicList.add(topicData)

                    runOnUiThread {
                        mTopicAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}




















