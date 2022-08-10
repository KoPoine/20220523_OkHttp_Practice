package com.neppplus.a20220523_okhttp_practice.models

import org.json.JSONObject
import java.io.Serializable

class TopicData : Serializable {

//    생성자 : 기본 생성자 유지.
//    멤버변수만 따로 추가 -> JSON 파싱해서 변수에 채워넣자.
//    멤버변수 : 서버의 데이터를 보고 -> 담아주는 용도의 변수들로 만들자.

    var id = 0 // Int가 들어올 자리라는 표식
    var title = "" // String이 들어올 자리
    var imageURL = ""

    var replyCount = 0

//    토론 주제의 하위 목록 -> 선택 진영 (SideData) 목록 (ArrayList)
    val sideList = ArrayList<SideData>()

//    내가 투표 한 진영은 어딘가? => null일 가능성도 있다.
    var mySide : SideData? = null

//    주제 정보를 담고있는 JSONObject 가 들어오면 > TopicData로 변화해 주는 함수 > static 메쏘드
//        다른 화면들에서는 이 함수를 끌어다 사용.
    companion object {
        fun getTopicDataFromJson(jsonObj : JSONObject) : TopicData {
            val topicData = TopicData()

            topicData.id = jsonObj.getInt("id")
            topicData.title = jsonObj.getString("title")
            topicData.imageURL = jsonObj.getString("img_url")
            topicData.replyCount = jsonObj.getInt("reply_count")

            val sideArr = jsonObj.getJSONArray("sides")
            for (i in 0 until sideArr.length()) {
                val sideObj = sideArr.getJSONObject(i)

                val sideData = SideData.getSideDataFromJson(sideObj)

                topicData.sideList.add(sideData)
            }

            return topicData
        }
    }

}